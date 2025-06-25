// Elevator.java
import java.util.PriorityQueue;
import java.util.Comparator;

public class Elevator extends Thread {
    private int currentFloor = 0;
    private Direction direction = Direction.IDLE;

    private PriorityQueue<Request> upQueue = new PriorityQueue<>(Comparator.comparingInt(Request::getFloor));
    private PriorityQueue<Request> downQueue = new PriorityQueue<>((a, b) -> b.getFloor() - a.getFloor());

    public void requestElevator(int floor) {
        if (floor > currentFloor) {
            upQueue.add(new Request(floor, Direction.UP));
        } else if (floor < currentFloor) {
            downQueue.add(new Request(floor, Direction.DOWN));
        }
    }

    public void run() {
        while (true) {
            try {
                if (!upQueue.isEmpty()) {
                    direction = Direction.UP;
                    Request request = upQueue.poll();
                    moveToFloor(request.getFloor());
                } else if (!downQueue.isEmpty()) {
                    direction = Direction.DOWN;
                    Request request = downQueue.poll();
                    moveToFloor(request.getFloor());
                } else {
                    direction = Direction.IDLE;
                    System.out.println(" The Elevator is currently on the " + currentFloor +  " Floor");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveToFloor(int floor) throws InterruptedException {
        while (currentFloor != floor) {
            if (currentFloor < floor) {
                currentFloor++;
            } else {
                currentFloor--;
            }
            System.out.println("Moving to floor " + currentFloor);
            Thread.sleep(500); // Simulate time to move one floor
        }
        System.out.println("Elevator arrived at floor " + currentFloor);
    }
}
