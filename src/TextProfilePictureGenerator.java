import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class TextProfilePictureGenerator {

  public static void main(String[] args) throws IOException {

    TextProfilePictureGenerator.generateOvalTextProfilePicture(100, 100,
        "李白",
        new Font("Yuanti SC", Font.PLAIN, 40),
        new Color(Integer.parseInt("e3f2fd", 16)),
        new Color(Integer.parseInt("ff8a65", 16)),
        "/Users/Shared/test/test1.png");

    TextProfilePictureGenerator.generateRectTextProfilePicture(100, 100,
        "鲁班",
        new Font("Wawati SC", Font.PLAIN, 50),
        new Color(Integer.parseInt("e0e0e0", 16)),
        new Color(Integer.parseInt("00897b", 16)),
        "/Users/Shared/test/test2.png");

    TextProfilePictureGenerator.generateRoundRectTextProfilePicture(100, 100,
        50, 50,
        "露娜",
        new Font("Yuanti SC", Font.PLAIN, 40),
        new Color(Integer.parseInt("e3f2fd", 16)),
        new Color(Integer.parseInt("7e57c2", 16)),
        "/Users/Shared/test/test3.png");

    int[] xPoints = new int[]{0, 140, 150, 140, 0, 10};
    int[] yPoints = new int[]{0, 0, 75, 150, 150, 75};
    TextProfilePictureGenerator.generatePolygonTextProfilePicture(xPoints,
        yPoints,
        "明世隐",
        new Font("Wawati SC", Font.PLAIN, 40),
        new Color(Integer.parseInt("212121", 16)),
        new Color(Integer.parseInt("0288d1", 16)),
        "/Users/Shared/test/test4.png");
  }

  /**
   * 生成椭圆背景、圆形背景的文字头像图片到指定文件.
   *
   * @throws IOException 当文件路径未指定或已经存在同名文件时
   */
  public static void generateOvalTextProfilePicture(int width,
                                                    int height,
                                                    String text,
                                                    Font textFont,
                                                    Color textColor,
                                                    Color backgroundColor,
                                                    String outputPicturePath) throws IOException {

    // 参数检查
    checkParamNotNull(text, textFont, textColor, backgroundColor, outputPicturePath);
    // 创建缓冲图像
    BufferedImage image = generateBufferedImage(width, height);
    Graphics2D graphics2D = image.createGraphics();
    // 抗锯齿
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    // 画背景
    drawOvalBackground(graphics2D, width, height, backgroundColor);
    // 画文字
    drawText(graphics2D, width, height, text, textFont, textColor);
    graphics2D.dispose();
    // 写到文件
    writeToOutputFile(image, outputPicturePath);
  }

  /**
   * 生成矩形背景的文字头像图片到指定文件.
   *
   * @throws IOException 当文件路径未指定或已经存在同名文件时
   */
  public static void generateRectTextProfilePicture(int width,
                                                    int height,
                                                    String text,
                                                    Font textFont,
                                                    Color textColor,
                                                    Color backgroundColor,
                                                    String outputPicturePath) throws IOException {
    // 参数检查
    checkParamNotNull(text, textFont, textColor, backgroundColor, outputPicturePath);
    // 创建缓冲图像
    BufferedImage image = generateBufferedImage(width, height);
    Graphics2D graphics2D = image.createGraphics();
    // 抗锯齿
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    // 画背景
    drawRectBackground(graphics2D, width, height, backgroundColor);
    // 画文字
    drawText(graphics2D, width, height, text, textFont, textColor);
    graphics2D.dispose();
    // 写到文件
    writeToOutputFile(image, outputPicturePath);
  }

  /**
   * 生成圆角矩形背景的文字头像图片到指定文件.
   *
   * @throws IOException 当文件路径未指定或已经存在同名文件时
   */
  public static void generateRoundRectTextProfilePicture(int width,
                                                         int height,
                                                         int arcWidth,
                                                         int arcHeight,
                                                         String text,
                                                         Font textFont,
                                                         Color textColor,
                                                         Color backgroundColor,
                                                         String outputPicturePath) throws IOException {
    // 参数检查
    checkParamNotNull(text, textFont, textColor, backgroundColor, outputPicturePath);
    // 创建缓冲图像
    BufferedImage image = generateBufferedImage(width, height);
    Graphics2D graphics2D = image.createGraphics();
    // 抗锯齿
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    // 画背景
    drawRoundRectBackground(graphics2D, width, height, arcWidth, arcHeight, backgroundColor);
    // 画文字
    drawText(graphics2D, width, height, text, textFont, textColor);
    graphics2D.dispose();
    // 写到文件
    writeToOutputFile(image, outputPicturePath);
  }

  /**
   * 生成多边形背景的文字头像图片到指定文件.
   *
   * @throws IOException 当文件路径未指定或已经存在同名文件时
   */
  public static void generatePolygonTextProfilePicture(int[] xPoints,
                                                       int[] yPoints,
                                                       String text,
                                                       Font textFont,
                                                       Color textColor,
                                                       Color backgroundColor,
                                                       String outputPicturePath) throws IOException {
    // 参数检查
    checkParamNotNull(text, textFont, textColor, backgroundColor, outputPicturePath);
    checkNotNull(xPoints, "xPoints is null");
    checkNotNull(yPoints, "yPoints is null");

    normalizePoints(xPoints);
    normalizePoints(yPoints);

    int width = Arrays.stream(xPoints).max().getAsInt();
    int height = Arrays.stream(yPoints).max().getAsInt();
    // 创建缓冲图像
    BufferedImage image = generateBufferedImage(width, height);
    Graphics2D graphics2D = image.createGraphics();
    // 抗锯齿
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    // 画背景
    drawPolygonBackground(graphics2D, xPoints, yPoints, xPoints.length, backgroundColor);
    // 画文字
    drawText(graphics2D, width, height, text, textFont, textColor);
    graphics2D.dispose();
    // 写到文件
    writeToOutputFile(image, outputPicturePath);
  }

  private static void normalizePoints(int[] points) {
    if (points == null || points.length == 0) {
      return;
    }

    int min = Arrays.stream(points).min().getAsInt();
    for (int i = 0; i < points.length; i++) {
      points[i] = points[i] - min;
    }
  }

  private static BufferedImage generateBufferedImage(int width, int height) {
    return new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
  }

  /**
   * 画椭圆、圆形背景
   */
  private static void drawOvalBackground(Graphics2D graphics2D, int width, int height, Color backgroundColor) {
    graphics2D.setColor(backgroundColor);
    graphics2D.fillOval(0, 0, width, height);
  }

  /**
   * 画矩形背景
   */
  private static void drawRectBackground(Graphics2D graphics2D, int width, int height, Color backgroundColor) {
    graphics2D.setColor(backgroundColor);
    graphics2D.fillRect(0, 0, width, height);
  }

  /**
   * 画圆角矩形背景
   */
  private static void drawRoundRectBackground(Graphics2D graphics2D, int width, int height, int arcWidth, int arcHeight, Color backgroundColor) {
    graphics2D.setColor(backgroundColor);
    graphics2D.fillRoundRect(0, 0, width, height, arcWidth, arcHeight);
  }

  /**
   * 画多边形背景
   */
  private static void drawPolygonBackground(Graphics2D graphics2D, int[] xPoints, int[] yPoints, int nPoints, Color backgroundColor) {
    graphics2D.setColor(backgroundColor);
    graphics2D.fillPolygon(xPoints, yPoints, nPoints);
  }

  /**
   * 画文字
   */
  private static void drawText(Graphics2D graphics2D, int width, int height, String text, Font textFont, Color textColor) {
    // 计算文字起始位置
    FontMetrics fontMetrics = graphics2D.getFontMetrics(textFont);
    int stringWidth = fontMetrics.stringWidth(text);
    int stringHeight = fontMetrics.getHeight();
    int stringAscent = fontMetrics.getAscent();
    int stringStartX = width / 2 - stringWidth / 2;
    int stringStartY = height / 2 - (stringHeight) / 2 + stringAscent;

    // 画文字内容
    graphics2D.setFont(textFont);
    graphics2D.setColor(textColor);
    graphics2D.drawString(text, stringStartX, stringStartY);
  }

  /**
   * 写到文件
   *
   * @throws IOException 当文件路径未指定或已经存在同名文件时
   */
  private static void writeToOutputFile(BufferedImage image, String outputPicturePath) throws IOException {
    if (outputPicturePath == null) {
      throw new IOException("Output picture path not specified.");
    }

    File outputPictureFile = new File(outputPicturePath);
    if (outputPictureFile.exists()) {
      outputPictureFile.delete();
      // throw new IOException(String.format("File %s already exists.", outputPicturePath));
    }

    ImageIO.write(image, "png", outputPictureFile);
  }

  /**
   * 检查参数不为null
   *
   * @throws RuntimeException 当有参数为null时
   */
  private static void checkParamNotNull(String text, Font textFont, Color textColor, Color backgroundColor, String outputPicturePath) {
    checkNotNull(text, "text not specified");
    checkNotNull(textFont, "textFont not specified");
    checkNotNull(textColor, "textColor not specified");
    checkNotNull(backgroundColor, "backgroundColor not specified");
    checkNotNull(outputPicturePath, "outputPicturePath not specified");
  }

  /**
   * 检查参数不为null
   *
   * @throws RuntimeException 当有参数为null时
   */
  private static void checkNotNull(Object object, String errMsgWhenNull) throws RuntimeException {
    if (object == null) {
      throw new RuntimeException(errMsgWhenNull);
    }
  }

  /**
   * 获取可用的字体name
   */
  public static String[] getAvailableFontFamilyNames() {
    GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
    return graphicsEnvironment.getAvailableFontFamilyNames();
  }

}
