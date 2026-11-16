
# Game-Development-Engine

An educational turn-based game engine written in Java. The console lets you create a scene, add enemies, change their behavior, advance the game one turn at a time, and switch rendering modes. The project demonstrates six design patterns and uses only the Java standard library.

## How the Game Works

The `Training Arena` demo starts with the hero at `(1, 1)` and a goblin at `(3, 1)`. The hero starts with 100 HP. In `idle` mode, enemies skip their turns. In `chase` mode, an enemy moves one tile toward the hero per turn, prioritizing horizontal movement. If the enemy starts its turn next to the hero, it deals 10 damage.

Both rendering modes use console output. The `2d` mode displays `(x, y)`, while `3d` simulates three-dimensional rendering by adding `z = 0`. Switching modes preserves character positions and health.

## Running the Project

Tested with JDK 25.

**IntelliJ IDEA.** Open the project folder, select JDK 25, and run `gameengine.Main`. If IntelliJ does not recognize the source files, mark `src` as a **Sources Root**.


## Console Commands

Start with `Create`. Commands are case-sensitive. Enter each argument separately when prompted.

| Command | Action | Arguments |
| --- | --- | --- |
| `Create` | Create the demo scene | |
| `Spawn` | Add an enemy | Name and integer `x` and `y` coordinates |
| `Behavior` | Change the behavior of all enemies | `idle` or `chase` |
| `Next` | Advance the game by one turn | |
| `Render` | Switch rendering modes and display the scene | `2d` or `3d` |
| `Status` | Show the scene name, enemy count, and hero HP | |
| `Logoff` | Unsubscribe `GameLogger` from health updates | |
| `Exit` | Close the program | |

## Design Patterns

| Pattern | Classes | Role |
| --- | --- | --- |
| **Builder** | `Level.Builder` | Assembles a scene from a player and a list of enemies. |
| **Factory Method** | `GameObjectFactory`, `PlayerFactory`, `EnemyFactory` | Concrete factories override `create()` to produce the appropriate character type. |
| **Strategy** | `BehaviorStrategy`, `IdleStrategy`, `ChaseStrategy` | Lets existing enemies change their behavior during the game. |
| **Bridge** | `GameObject`, `Renderer`, `ConsoleRenderer2d`, `ConsoleRenderer3d` | Lets game objects delegate display to the selected renderer. |
| **Observer** | `Player`, `HealthObserver`, `HealthDisplay`, `GameLogger` | Notifies subscribers when the player's health changes. |
| **Facade** | `GameFacade`, `Main` | `Main` reads console commands and calls facade methods. The facade coordinates scene creation, turns, and rendering. |

| Student | Individually implemented patterns | Main responsibilities |
|---|---|---|
| Sungat | Builder, Factory Method | Implemented player and enemy factories, level construction through `Level.Builder`, and their initial integration into `Main`. |
| Maksat | Strategy, Observer | Implemented idle and chase behaviors, enemy movement and attacks, health notifications, and observer subscription and unsubscription. |
| Tole | Bridge, Facade | Implemented interchangeable 2D and simulated 3D console renderers, `GameFacade`, and the interactive console menu. |
## Demo Walkthrough

1. Enter `Create`, then `Next`. The goblin stays at `(3, 1)`.
2. Enter `Behavior`, then `chase`. The first `Next` moves the goblin to `(2, 1)`. The second `Next` makes it attack. The hero drops to 90 HP and both observers report the change.
3. Enter `Render`, then `3d` and `Status`. The goblin is displayed at `(2, 1, 0)` and the hero still has 90 HP.
4. Enter `Logoff`, then `Next`. The next attack drops the hero to 80 HP. Only `HealthDisplay` reports the change.
5. Enter `Spawn`, followed by `Goblin 2`, `4`, and `2` on separate lines. `Status` now shows two enemies and 80 HP.

## Authors

mx, tole, suchkat
