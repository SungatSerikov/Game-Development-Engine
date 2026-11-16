package gameengine.model;
import gameengine.Renderer.Renderer;
import java.util.Objects;

public abstract class GameObject {
    private final String name;
    private int x;
    private int y;
    private Renderer renderer;
    protected GameObject(String name, int x, int y) {
        this.name = name;
        setPosition(x, y);
    }

    public String getName() { return name; }
    public int getX() { return x; }
    public int getY() { return y; }

    public final void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public final void setRenderer(Renderer renderer) {
        this.renderer = Objects.requireNonNull(renderer);
    }

    public final void render() {
        if (renderer == null) {
            throw new IllegalStateException("Assign a renderer before rendering " + name);
        }
        renderer.render(name, x, y);
    }
}
