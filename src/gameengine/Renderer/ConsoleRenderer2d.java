package gameengine.Renderer;

public class ConsoleRenderer2d implements Renderer {
    @Override
    public void render(String name, int x, int y) {
        System.out.printf("2D: %s at (%d, %d)%n", name, x, y);
    }
}