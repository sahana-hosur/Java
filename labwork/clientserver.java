import java.util.concurrent.*;

public class clientserver {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Thread serverThread = new Thread(new Server(queue));
        Thread clientThread = new Thread(new Client(queue));

        serverThread.start();
        clientThread.start();
        
        try {
            clientThread.join();
            serverThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Server implements Runnable {
    private BlockingQueue<String> queue;

    public Server(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                String request = queue.take();
                System.out.println("Server received request: " + request);
                // Simulate processing
                Thread.sleep(1000);
                System.out.println("Server processed request: " + request);
                queue.add("Message Received");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
class Client implements Runnable {
    private BlockingQueue<String> queue;

    public Client(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            int requestCount = 5;
            for (int i = 0; i < requestCount; i++) {
                String request = "Request " + (i + 1);
                System.out.println("Client sending request: " + request);
                queue.put(request);
                String response = queue.take();
                System.out.println("Client received response: " + response);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
