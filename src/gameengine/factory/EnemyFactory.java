package gameengine.factory;

import gameengine.model.Enemy;

public class EnemyFactory extends GameObjectFactory {

    @Override
    public Enemy create(String name, int x, int y) {
        return new Enemy(name, x, y, 10);
    }
}