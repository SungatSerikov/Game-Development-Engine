package gameengine.model;

import gameengine.observer.HealthObserver;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

public class Player extends GameObject {
    private int health;
    private final Set<HealthObserver> observers = new LinkedHashSet<>();

    public Player(String name, int x, int y, int health) {
        super(name, x, y);
        if (health < 0) {
            throw new IllegalArgumentException("Health cannot be negative");
        }
        this.health = health;
    }

    public int getHealth() { return health; }

    public void subscribe(HealthObserver observer) {
        observers.add(Objects.requireNonNull(observer));
    }

    public void unsubscribe(HealthObserver observer) {
        observers.remove(observer);
    }

    public void takeDamage(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Damage cannot be negative");
        }
        int oldHealth = health;
        int newHealth = Math.max(0, health - amount);
        if (oldHealth == newHealth) {
            return;
        }
        health = newHealth;
        for (HealthObserver observer : new ArrayList<>(observers)) {
            observer.onHealthChanged(oldHealth, newHealth);
        }
    }
}
