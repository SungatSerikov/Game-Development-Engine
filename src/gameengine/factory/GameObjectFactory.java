package gameengine.factory;

import gameengine.model.GameObject;

public  abstract class GameObjectFactory {
    public abstract GameObject create(String name, int x, int y);
}
