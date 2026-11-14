package gameengine.observer;

public class GameLogger implements HealthObserver {
    @Override
    public void onHealthChanged(int oldHealth, int newHealth) {
        System.out.printf("[GameLogger] Player health changed: %d -> %d%n", oldHealth, newHealth);
    }
}
