import javax.swing.*;

public class MySwingApp {
   public static void main(String[] args) {
      JFrame frame = new JFrame("My Swing App");
      JLabel label = new JLabel("Hello, World!");

      frame.getContentPane().add(label);
      frame.pack();
      frame.setVisible(true);
   }
}
