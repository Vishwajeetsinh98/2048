import controller.GameController;
import model.MoveDirection;
import view.TerminalView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to 2048!");
        GameController gameController = new GameController();
        TerminalView.viewBoard(gameController.getBoard());

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Options:");
            System.out.println("w/a/s/d -> move");
            System.out.println("r -> reset");
            System.out.println("e -> exit\n");
            char command = scanner.next().charAt(0);
            if (command == 'r') {
                System.out.println("Resetting!");
                gameController.reset();
                TerminalView.viewBoard(gameController.getBoard());
            } else if (command == 'e') {
                System.out.println("Exiting!");
                break;
            } else if ("wasd".contains(String.valueOf(command))) {
                MoveDirection moveDirection = switch (command) {
                    case 'w' -> MoveDirection.UP;
                    case 'a' -> MoveDirection.LEFT;
                    case 's' -> MoveDirection.DOWN;
                    case 'd' -> MoveDirection.RIGHT;
                    default -> null;
                };
                gameController.move(moveDirection);
                TerminalView.viewBoard(gameController.getBoard());
            }
        }
    }
}