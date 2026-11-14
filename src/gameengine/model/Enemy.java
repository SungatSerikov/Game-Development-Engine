package gameengine.model;

public class Enemy extends GameObject {
    private final int damage;

    public Enemy(String name, int x, int y, int damage) {
        super(name, x, y);
        if (damage < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        this.damage = damage;
    }

    public int getDamage() { return damage; }
}
