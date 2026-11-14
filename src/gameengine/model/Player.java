package gameengine.model;

public class Player extends GameObject {
    private int health;

    public Player(String name, int x, int y, int health) {
        super(name, x, y);
        if (health < 0) {
            throw new IllegalArgumentException("Health cannot be negative");
        }
        this.health = health;
    }

    public int getHealth() { return health; }

    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        health = Math.max(0, health - amount);
    }
}
