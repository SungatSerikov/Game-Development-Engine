package gameengine.strategy;

import gameengine.model.Enemy;
import gameengine.model.Player;

public class IdleStrategy implements BehaviorStrategy {
    @Override
    public void execute(Enemy enemy, Player player) {
        // An idle enemy skips its turn.
    }
}
