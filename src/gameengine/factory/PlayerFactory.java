package gameengine.factory;

import gameengine.model.Player;

public class PlayerFactory extends GameObjectFactory {

    @Override
    public Player create(String name, int x, int y) {
        return new Player(name, x, y, 100);
    }
}