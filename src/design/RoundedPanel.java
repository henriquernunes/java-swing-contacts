package design;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedPanel extends JPanel {
    private int arcWidth;
    private int arcHeight;
    private Color backgroundColor;

    public RoundedPanel(int arcWidth, int arcHeight, Color backgroundColor) {
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        this.backgroundColor = backgroundColor;
        setOpaque(false);
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        RoundRectangle2D shape = new RoundRectangle2D.Double(
                0, 0, getWidth() - 1, getHeight() - 1,
                arcWidth, arcHeight
        );

        g2d.setColor(backgroundColor);
        g2d.fill(shape);

        super.paintComponent(g);
    }
}
