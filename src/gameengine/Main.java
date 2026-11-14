package gameengine;

import gameengine.model.Enemy;
import gameengine.model.Player;
import gameengine.observer.GameLogger;
import gameengine.observer.HealthDisplay;
import gameengine.strategy.ChaseStrategy;

public class Main {
    public static void main(String[] args) {
        Player player = new Player("Hero", 1, 1, 100);
        Enemy enemy = new Enemy("Goblin", 3, 1, 10);
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
    }
}