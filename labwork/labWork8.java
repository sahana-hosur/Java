package labwork;
import java.util.Scanner;

import java.util.concurrent.Semaphore;

public class labWork8 {
    
    private static final Semaphore semaphore = new Semaphore(1);
    private static final MessageHolder messageHolder = new MessageHolder();

    public static void main(String[] args) 
    {
        Thread serverThread = new Thread(new Server());
        Thread clientThread = new Thread(new Client());

        clientThread.start();
        
        try 
        {
            Thread.sleep(1000); // give client time to send message
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        serverThread.start();
    }

    static class Server implements Runnable {
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("------------------------------------");

                String message = messageHolder.getMessage();
                System.out.println("Server received message: " + message);

                System.out.println("......");
             

                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Client implements Runnable {
        @Override
        public void run() {
            try {
                semaphore.acquire();

                Scanner scanner = new Scanner(System.in);
                System.out.println("Please type your message: ");
                String message = scanner.nextLine();

                messageHolder.setMessage(message);

//                System.out.println("Client is ready");
                System.out.println("Client sent message: " + message);
                System.out.println("Client waiting for acknowledgement");
                System.out.println("Acknowledgement: Message Received - " + message);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    static class MessageHolder {
        private String message;

        synchronized void setMessage(String message) {
            this.message = message;
        }

        synchronized String getMessage() {
            return message;
        }
    }
}
