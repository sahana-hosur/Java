import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class HitMe extends JPanel {
    private static final int BOX_SIZE = 40; // in pixels
    private static final int PANEL_WIDTH = 400; // in pixels
    private static final int PANEL_HEIGHT = 400; // in pixels
    private static final int HIT_MISS_LABEL_WIDTH = 100; // in pixels
    private static final int HIT_MISS_LABEL_HEIGHT = 20; // in pixels
    private static final int MAX_BOX_POSITION_X = PANEL_WIDTH - BOX_SIZE;
    private static final int MAX_BOX_POSITION_Y = PANEL_HEIGHT - BOX_SIZE;
    private static final Color BOX_BORDER_COLOR = Color.BLACK;
    private static final Color BOX_FILL_COLOR = Color.RED;
    private static final Random RANDOM = new Random();

    private int boxPositionX;
    private int boxPositionY;
    private int hitCount;
    private int missCount;

    public HitMe() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = e.getPoint();
                if (point.x >= boxPositionX && point.x <= boxPositionX + BOX_SIZE
                        && point.y >= boxPositionY && point.y <= boxPositionY + BOX_SIZE) {
                    hitCount++;
                } else {
                    missCount++;
                }
                repaint();
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the box
        if (boxPositionX == 0 && boxPositionY == 0) { // The box is not visible, so choose a new position
            boxPositionX = RANDOM.nextInt(MAX_BOX_POSITION_X + 1);
            boxPositionY = RANDOM.nextInt(MAX_BOX_POSITION_Y + 1);
        }
        g.setColor(BOX_BORDER_COLOR);
        g.drawRect(boxPositionX, boxPositionY, BOX_SIZE, BOX_SIZE);
        g.setColor(BOX_FILL_COLOR);
        g.fillRect(boxPositionX + 1, boxPositionY + 1, BOX_SIZE - 1, BOX_SIZE - 1);
        // Draw the hit/miss counters
        String hitMissText = "Hit: " + hitCount + "   Miss: " + missCount;
        g.drawString(hitMissText, PANEL_WIDTH - HIT_MISS_LABEL_WIDTH, HIT_MISS_LABEL_HEIGHT);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hit-Miss Game");
        HitMe gamePanel = new HitMe();
        gamePanel.setPreferredSize(new java.awt.Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        frame.getContentPane().add(gamePanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        while (true) { // Continuously repaint the panel with a delay of 1 second
            gamePanel.boxPositionX = 0;
            gamePanel.boxPositionY = 0;
            gamePanel.repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
