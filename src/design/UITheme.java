package design;

import java.awt.*;

public class UITheme {
  public static final Color PRIMARY_BLUE = new Color(0, 122, 255);
  public static final Color SECONDARY_BLUE = new Color(50, 170, 255);
  public static final Color LIGHT_BLUE = new Color(230, 244, 255);
  public static final Color DARK_BACKGROUND = new Color(242, 242, 247);
  public static final Color WHITE = new Color(255, 255, 255);
  public static final Color LIGHT_GRAY = new Color(200, 200, 205);
  public static final Color DARK_GRAY = new Color(100, 100, 105);
  public static final Color TEXT_DARK = new Color(51, 51, 51);
  public static final Color TEXT_LIGHT = new Color(150, 150, 150);
  public static final Color DELETE_RED = new Color(255, 59, 48);
  public static final Color SUCCESS_GREEN = new Color(52, 199, 89);
  public static final Color WARNING_ORANGE = new Color(255, 149, 0);

  public static Font getHeaderFont() {
    return new Font("Segoe UI", Font.BOLD, 28);
  }

  public static Font getTitleFont() {
    return new Font("Segoe UI", Font.BOLD, 18);
  }

  public static Font getSubtitleFont() {
    return new Font("Segoe UI", Font.PLAIN, 14);
  }

  public static Font getRegularFont() {
    return new Font("Segoe UI", Font.PLAIN, 13);
  }

  public static Font getSmallFont() {
    return new Font("Segoe UI", Font.PLAIN, 11);
  }
}
