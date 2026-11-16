package gameengine;

import gameengine.Renderer.ConsoleRenderer2d;
import gameengine.Renderer.ConsoleRenderer3d;
import gameengine.Renderer.Renderer;
import gameengine.model.Enemy;
import gameengine.model.Player;
import gameengine.observer.GameLogger;
import gameengine.observer.HealthDisplay;
import gameengine.strategy.ChaseStrategy;
import gameengine.factory.PlayerFactory;
import gameengine.factory.EnemyFactory;
import gameengine.model.Level;

public class Main {
    public static void main(String[] args) {
        PlayerFactory playerFactory = new PlayerFactory();
        EnemyFactory enemyFactory = new EnemyFactory();

        Level level = new Level.Builder()
                .setName("Training Arena")
                .setPlayer(playerFactory.create("Hero", 1, 1))
                .addEnemy(enemyFactory.create("Goblin", 3, 1))
                .build();

        Player player = level.getPlayer();
        Enemy enemy = level.getEnemies().get(0);

        System.out.println("Level: " + level.getName());
        System.out.println("Enemies: " + level.getEnemies().size());

        GameLogger logger = new GameLogger();
        player.subscribe(new HealthDisplay());
        player.subscribe(logger);

        enemy.update(player);
        System.out.printf("Idle turn: Goblin at (%d, %d)%n", enemy.getX(), enemy.getY());

        enemy.setBehavior(new ChaseStrategy());
        enemy.update(player);
        System.out.printf("Chase turn: Goblin at (%d, %d)%n", enemy.getX(), enemy.getY());

        System.out.println("Attack with both observers:");
        enemy.update(player);

        player.unsubscribe(logger);
        System.out.println("Attack after removing the logger:");
        enemy.update(player);

        //renderer
        Renderer renderer2d = new ConsoleRenderer2d();
        Renderer renderer3d = new ConsoleRenderer3d();

        player.setRenderer(renderer2d);
        enemy.setRenderer(renderer2d);

        player.render();
        enemy.render();

        player.setRenderer(renderer3d);
        enemy.setRenderer(renderer3d);

        enemy.render();
        player.render();
        
    }
}