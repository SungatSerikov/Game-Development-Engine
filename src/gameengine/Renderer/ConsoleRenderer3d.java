package gameengine.Renderer;

public class ConsoleRenderer3d implements Renderer {
    @Override
    public void render(String name, int x, int y) {
        //0 is placeholder value since project doesn't support 3d yet
        System.out.printf("3D: %s at (%d, %d, %d)%n", name, x, y, 0);
    }
}