package gameengine.observer;

public interface HealthObserver {
    void onHealthChanged(int oldHealth, int newHealth);
}
