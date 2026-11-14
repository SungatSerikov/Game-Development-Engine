package gameengine.model;

import gameengine.strategy.BehaviorStrategy;
import gameengine.strategy.IdleStrategy;
import java.util.Objects;

public class Enemy extends GameObject {
    private final int damage;
    private BehaviorStrategy behavior = new IdleStrategy();

    public Enemy(String name, int x, int y, int damage) {
        super(name, x, y);
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        this.damage = damage;
    }

    public int getDamage() { return damage; }

    public void setBehavior(BehaviorStrategy behavior) {
        this.behavior = Objects.requireNonNull(behavior);
    }

    public void update(Player player) {
        behavior.execute(this, player);
    }
}
