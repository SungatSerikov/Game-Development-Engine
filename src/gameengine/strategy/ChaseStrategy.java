package gameengine.strategy;

import gameengine.model.Enemy;
import gameengine.model.Player;

public class ChaseStrategy implements BehaviorStrategy {
    @Override
    public void execute(Enemy enemy, Player player) {
        if (player.getHealth() == 0) {
            return;
        }

        int x = enemy.getX();
        int y = enemy.getY();
        long dx = (long) player.getX() - x;
        long dy = (long) player.getY() - y;
        if (Math.abs(dx) + Math.abs(dy) == 1) {
            player.takeDamage(enemy.getDamage());
        } else if (dx != 0) {
            enemy.setPosition(x + Long.signum(dx), y);
        } else if (dy != 0) {
            enemy.setPosition(x, y + Long.signum(dy));
        }
    }
}
