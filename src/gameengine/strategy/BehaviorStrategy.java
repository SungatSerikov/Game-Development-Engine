package gameengine.strategy;

import gameengine.model.Enemy;
import gameengine.model.Player;

public interface BehaviorStrategy {
    void execute(Enemy enemy, Player player);
}
