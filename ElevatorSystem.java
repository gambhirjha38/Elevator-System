// ElevatorSystem.java (Main Class)
import java.util.Scanner;

public class ElevatorSystem {1

    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        elevator.start();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter floor request (-1 to exit): ");
            int floor = scanner.nextInt();
            if (floor == -1) break;
            elevator.requestElevator(floor);
        }
        scanner.close();
        System.exit(0); // Stop program after input ends
    }
}
