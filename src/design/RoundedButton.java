package design;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedButton extends JButton {
  private int arcWidth;
  private int arcHeight;

  public RoundedButton(String text, int arcWidth, int arcHeight) {
    super(text);
    this.arcWidth = arcWidth;
    this.arcHeight = arcHeight;
    setContentAreaFilled(false);
    setFocusPainted(false);
    setBorderPainted(false);
    setFont(UITheme.getRegularFont());
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Desenha o fundo arredondado
    RoundRectangle2D shape = new RoundRectangle2D.Double(
        0, 0, getWidth() - 1, getHeight() - 1,
        arcWidth, arcHeight);

    g2d.setColor(getBackground());
    g2d.fill(shape);

    // Desenha o texto
    super.paintComponent(g);
  }

  @Override
  protected void paintBorder(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    RoundRectangle2D shape = new RoundRectangle2D.Double(
        0, 0, getWidth() - 1, getHeight() - 1,
        arcWidth, arcHeight);

    g2d.setColor(getBackground());
    g2d.setStroke(new BasicStroke(1));
    g2d.draw(shape);
  }

  @Override
  public boolean contains(int x, int y) {
    RoundRectangle2D shape = new RoundRectangle2D.Double(
        0, 0, getWidth() - 1, getHeight() - 1,
        arcWidth, arcHeight);
    return shape.contains(x, y);
  }
}
