package gameengine;
import gameengine.Facade.GameFacade;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        GameFacade game = new GameFacade();
        System.out.println("""
            ====Main Menu====
            Create - creates a demo level, uses 2d renderer and idle state by default
            Spawn - spawns an enemy, requires name, x, y
            Behavior - sets enemy behavior: idle or chase
            Next - next iteration of the game
            Render - renders a game with chosen renderer: 2d or 3d
            Status - shows the game's status
            Logoff - unsubscribe to health logger
            Exit - exit
        """);

        try (Scanner input = new Scanner(System.in)) {
            while (true) {
                try {
                    switch (ask(input, "> ")) {
                        case "Create":
                            game.createDemoLevel();
                            break;
                        case "Spawn": {
                                String name = ask(input, "Enemy's name: ");
                                int x = Integer.parseInt(ask(input, "X: "));
                                int y = Integer.parseInt(ask(input, "Y: "));
                                game.spawnEnemy(name, x, y);
                            }
                            break;
                        case "Behavior":
                            game.setEnemyBehavior(
                                ask(input, "idle or chase: "));
                            break;
                        case "Next":
                            game.nextTurn();
                            break;
                        case "Render":
                            game.setRenderMode(
                                ask(input, "2d or 3d: "));
                            break;
                        case "Status":
                            game.showStatus();
                            break;
                        case "Logoff":
                            game.disableHealthLog();
                            break;
                        case "Exit":
                            return;
                        default:
                            System.out.println("Unknown command.");
                            break;
                    }
                } catch (NumberFormatException error) {
                    System.out.println("Coordinates must be an integers.");
                } catch (IllegalArgumentException | IllegalStateException error) {
                    System.out.println(error.getMessage());
                } catch (NoSuchElementException endOfInput) {
                    return;
                }
            }
        }
    }

    private static String ask(Scanner input, String prompt) {
        System.out.print(prompt);
        return input.nextLine().trim();
    }
}


