package gameengine.model;

public abstract class GameObject {
    private final String name;
    private int x;
    private int y;

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
}
