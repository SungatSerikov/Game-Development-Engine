package gameengine.Renderer;

public class ConsoleRenderer3d implements Renderer {
    @Override
    public void render(String name, int x, int y) {
        System.out.printf("3D: %s at (%d, %d, %d)%n", name, x, y, 0);
    }
}