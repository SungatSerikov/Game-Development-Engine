package gameengine.Facade;

import gameengine.factory.EnemyFactory;
import gameengine.factory.PlayerFactory;
import gameengine.model.Enemy;
import gameengine.model.Level;
import gameengine.model.Player;
import gameengine.observer.GameLogger;
import gameengine.observer.HealthDisplay;
import gameengine.Renderer.ConsoleRenderer2d;
import gameengine.Renderer.ConsoleRenderer3d;
import gameengine.Renderer.Renderer;
import gameengine.strategy.BehaviorStrategy;
import gameengine.strategy.ChaseStrategy;
import gameengine.strategy.IdleStrategy;
import java.util.Objects;

public class GameFacade {
    private final PlayerFactory playerFactory = new PlayerFactory();
    private final EnemyFactory enemyFactory = new EnemyFactory();
    private final GameLogger logger = new GameLogger();
    private Renderer renderer = new ConsoleRenderer2d();
    private BehaviorStrategy behavior = new IdleStrategy();
    private Level level;

    public GameFacade() {
    }

    public GameFacade(Level level, Renderer renderer) {
        this.level = Objects.requireNonNull(level);
        this.renderer = Objects.requireNonNull(renderer);
        applyRenderer(level);
        renderLevel(level);
    }

    public void createDemoLevel() {
        //uses demo values
        Player player = playerFactory.create("Hero", 1, 1);
        player.subscribe(new HealthDisplay());
        player.subscribe(logger);

        Enemy enemy = enemyFactory.create("Goblin", 3, 1);
        behavior = new IdleStrategy();
        enemy.setBehavior(behavior);

        level = new Level.Builder()
                .setName("Training Arena")
                .setPlayer(player)
                .addEnemy(enemy)
                .build();
        applyRenderer(level);
        renderLevel(level);
    }

    public void spawnEnemy(String name, int x, int y) {
        Level current = requireLevel();
        Enemy enemy = enemyFactory.create(name, x, y);
        enemy.setRenderer(renderer);
        enemy.setBehavior(behavior);

        Level.Builder builder = new Level.Builder()
                .setName(current.getName())
                .setPlayer(current.getPlayer());
        for (Enemy subEnemy : current.getEnemies()){
            builder.addEnemy(subEnemy);
        }
        level = builder.addEnemy(enemy).build();
        renderLevel(level);
    }

    public void setEnemyBehavior(String mode) {
        setEnemyBehavior(switch (mode) {
            case "idle":
                yield new IdleStrategy();
            case "chase":
                yield new ChaseStrategy();
            default:
                throw new IllegalArgumentException("Choose idle or chase.");
        });
    }

    public void setEnemyBehavior(BehaviorStrategy strategy) {
        Level current = requireLevel();
        behavior = Objects.requireNonNull(strategy);
        for (Enemy enemy : current.getEnemies()) {
            enemy.setBehavior(behavior);
        }
    }

    public void nextTurn() {
        Level current = requireLevel();
        Player player = current.getPlayer();
        for (Enemy enemy : current.getEnemies()) {
            enemy.update(player);
        }
        renderLevel(current);
    }

    public void setRenderMode(String mode) {
        Level current = requireLevel();
        renderer = switch (mode) {
            case "2d":
                yield new ConsoleRenderer2d();
            case "3d":
                yield new ConsoleRenderer3d();
            default:
                throw new IllegalArgumentException("Choose 2d or 3d.");
        };
        applyRenderer(current);
        renderLevel(current);
    }

    public void showStatus() {
        Level current = requireLevel();
        System.out.printf("%s | Enemies: %d | HP: %d%n",
                current.getName(), current.getEnemies().size(), current.getPlayer().getHealth());
    }

    public void disableHealthLog() {
        requireLevel().getPlayer().unsubscribe(logger);
        System.out.println("Health log disabled.");
    }

    private Level requireLevel() {
        if (level == null) {
            throw new IllegalStateException("Create a scene first.");
        }
        return level;
    }

    private void applyRenderer(Level current) {
        current.getPlayer().setRenderer(renderer);
        for (Enemy subEnemy : current.getEnemies()){
            subEnemy.setRenderer(renderer);
        }
    }

    private void renderLevel(Level current) {
        current.getPlayer().render();
        for (Enemy subEnemy : current.getEnemies()){
            subEnemy.render();
        }
    }
}
