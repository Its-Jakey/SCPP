package slvm;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class VMGraphics {
    private int gp;
    private java.util.List<String> buffer;
    public static final Font font;

    static {
        try {
            font = Font.createFont(Font.TRUETYPE_FONT,
                    new File("font.ttf")).deriveFont(Font.PLAIN, 8);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private int getIntValue(String value) {
        try {
            return (int) Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    private String getNext() {
        if (gp >= buffer.size())
            throw new VMGraphicsException("Graphics pointer out of range");
        return buffer.get(gp++);
    }
    private int getNextInt() {
        return getIntValue(getNext());
    }

    public void process(java.util.List<String> buffer, Graphics2D g2d) {
        this.buffer = buffer;
        int x = 2, y = 12;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, 480, 360);
        g2d.setFont(font);
        gp = 0;

        while (gp < buffer.size()) {

            switch (buffer.get(gp++)) {
                case "setStroke" -> g2d.setStroke(new BasicStroke(getNextInt()));
                case "putPixel" -> g2d.fillRect(getNextInt(), getNextInt(), 1, 1);
                case "drawLine" -> {
                    int x0 = getNextInt(), y0 = getNextInt(), x1 = getNextInt(), y1 = getNextInt();
                    //System.out.println("drawLine " + x0 + ", " + y0 + ", " + x1 + ", " + y1);
                    g2d.drawLine(x0, y0, x1, y1);
                }
                case "fillRect" -> g2d.fillRect(getNextInt(), getNextInt(), getNextInt(), getNextInt());
                case "writeString" -> {
                    String toWrite = getNext();

                    for (char c : toWrite.toCharArray()) {
                        g2d.drawString(String.valueOf(c), x + 8, y);
                        x += 8;

                        if (x >= 486) {
                            y += 8;
                            x = 2;
                        }
                    }
                }
                case "cr" -> {
                    y += 8;
                    x = 2;
                }
                case "goto" -> {
                    x = getNextInt();
                    y = getNextInt();
                }
                case "setColor" -> {
                    int color = getNextInt();
                    //System.out.println("setColor " + color);
                    g2d.setColor(new Color(color));
                }
                default -> throw new VMGraphicsException("Unknown GPU instruction '" + buffer.get(gp) + "'");
            }
        }
    }
}
