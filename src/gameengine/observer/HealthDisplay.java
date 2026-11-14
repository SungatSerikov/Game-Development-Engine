package gameengine.observer;

public class HealthDisplay implements HealthObserver {
    @Override
    public void onHealthChanged(int oldHealth, int newHealth) {
        System.out.println("[HealthDisplay] HP: " + newHealth);
    }
}
