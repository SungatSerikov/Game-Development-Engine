package gameengine.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Level {
    private final String name;
    private final Player player;
    private final List<Enemy> enemies;

    private Level(Builder builder) {
        this.name = builder.name;
        this.player = builder.player;
        this.enemies = List.copyOf(builder.enemies);
    }

    public String getName() {
        return name;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public static class Builder {
        private String name = "Default Level";
        private Player player;
        private final List<Enemy> enemies = new ArrayList<>();

        public Builder setName(String name) {
            this.name = Objects.requireNonNull(name);
            return this;
        }

        public Builder setPlayer(Player player) {
            this.player = Objects.requireNonNull(player);
            return this;
        }

        public Builder addEnemy(Enemy enemy) {
            enemies.add(Objects.requireNonNull(enemy));
            return this;
        }

        public Level build() {
            if (player == null) {
                throw new IllegalStateException("Cannot create a level without a player");
            }
            return new Level(this);
        }
    }
}