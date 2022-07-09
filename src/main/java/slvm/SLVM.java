package slvm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class SLVM {
    private final String[] instructions;
    private String[] ram = new String[0xFFFFFFF];
    private boolean[] usedAddresses = new boolean[ram.length];
    static int pc;
    private String a;
    private boolean isMouseDown;
    private final List<Integer> keysPressed;
    private final LinkedHashMap<String, Integer> variables;
    private final Stack<Integer> stack;
    private int vp;
    private boolean running;
    private List<String> buffer;
    private final VMGraphics graphics;
    private final JPanel panel;
    private long runStart;
    private BufferedImage image;

    private double getIntValue(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    private String getNext() {
        if (pc >= instructions.length)
            throw new VMException("PC out of program range");
        return instructions[pc++];
    }
    private double getNextInt() {
        return getIntValue(getNext());
    }
    private int getVar(String varName) {
        if (!variables.containsKey(varName))
            variables.put(varName, allocateMemory(1));
        return variables.get(varName);
    }
    private List<Cluster> createClusters(int maxSize) {
        List<Cluster> ret = new ArrayList<>();

        for (int i = 0; i < ram.length; i++) {
            if (!usedAddresses[i]) {
                int addr = i;

                while (i < ram.length && !usedAddresses[i]) {
                    if (i - addr >= maxSize) {
                        ret.add(new Cluster(addr, i - addr));
                        return ret;
                    }
                    i++;
                }
                int size = i - addr;
                i--;

                ret.add(new Cluster(addr, size));
            }
        }

        return ret;
    }
    private int allocateMemory(int words) {
        List<Cluster> clusters = createClusters(words);

        for (Cluster cluster : clusters) {
            if (cluster.size() >= words) {
                int ret = cluster.address();

                for (int i = ret; i < ret + words; i++)
                    usedAddresses[i] = true;
                return ret;
            }
        }
        throw new VMException("Could not allocate " + words + " words because there is no free memory");
    }
    private String getNextVarValue() {
        return ram[getVar(getNext())];
    }

    private void setNextVarValue(String value) {
        ram[getVar(getNext())] = value;
    }

    private boolean getBoolValue(String value) {
        return getIntValue(value) > 0;
    }
    private boolean getNextBool() {
        return getBoolValue(getNext());
    }
    private String getBool(boolean bool) {
        return String.valueOf(bool ? 1 : 0);
    }
    private String getInt(double val) {
        return String.valueOf(val);
    }
    private double getNextIntVar() {
        return getIntValue(getNextVarValue());
    }

    private int mapKey(String key) {
        return switch (key) {
            case "left arrow" -> KeyEvent.VK_LEFT;
            case "right arrow" -> KeyEvent.VK_RIGHT;
            case "up arrow" -> KeyEvent.VK_UP;
            case "down arrow" -> KeyEvent.VK_DOWN;
            case "shift" -> KeyEvent.VK_SHIFT;
            case "enter" -> KeyEvent.VK_ENTER;
            case "space" -> KeyEvent.VK_SPACE;
            case "escape" -> KeyEvent.VK_ESCAPE;
            default -> KeyEvent.getExtendedKeyCodeForChar(key.charAt(0));
        };
    }


    public SLVM(String program) {
        this.instructions = program.split("\n");
        this.variables = new LinkedHashMap<>();
        this.running = true;
        this.buffer = new ArrayList<>();
        this.stack = new Stack<>();
        this.graphics = new VMGraphics();
        this.keysPressed = new ArrayList<>();
        this.image = new BufferedImage(480, 360, BufferedImage.TYPE_INT_RGB);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(480, 360);
        
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                g.drawImage(image, 0, 0, frame.getWidth(), frame.getHeight(), this);
            }
        };
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!keysPressed.contains(e.getKeyCode()))
                    keysPressed.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keysPressed.remove((Object) e.getKeyCode());
            }
        });
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                isMouseDown = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isMouseDown = false;
            }
        });
        frame.add(panel);
        frame.setVisible(true);
    }

    public void run() {
        runStart = System.currentTimeMillis();
        while (running)
            execute(instructions[pc++]);
    }

    private void execute(String instruction) {
        switch (instruction) { //TODO: Implement graphics
            case "ldi" -> a = getNext();
            case "loadAtVar" -> a = getNextVarValue();
            case "storeAtVar" -> setNextVarValue(a);
            case "jts" -> {
                stack.push(pc + 1);
                pc = (int) getNextInt();
            }
            case "ret" -> pc = stack.pop();
            case "addWithVar" -> a = String.valueOf(getIntValue(a) + getIntValue(getNextVarValue()));
            case "subWithVar" -> a = String.valueOf(getIntValue(a) - getIntValue(getNextVarValue()));
            case "mulWithVar" -> a = String.valueOf(getIntValue(a) * getIntValue(getNextVarValue()));
            case "divWithVar" -> a = String.valueOf(getIntValue(a) / getIntValue(getNextVarValue()));
            case "bitwiseLsfWithVar" -> a = String.valueOf(((int) getIntValue(a)) << ((int) getIntValue(getNextVarValue())));
            case "bitwiseRsfWithVar" -> a = String.valueOf(((int) getIntValue(a)) >> ((int) getIntValue(getNextVarValue())));
            case "bitwiseAndWithVar" -> a = String.valueOf(((int) getIntValue(a)) & ((int) getIntValue(getNextVarValue())));
            case "bitwiseOrWithVar" -> a = String.valueOf(((int) getIntValue(a)) | ((int) getIntValue(getNextVarValue())));
            case "modWithVar" -> a = String.valueOf(getIntValue(a) % getIntValue(getNextVarValue()));
            case "print" -> System.out.print(a);
            case "println" -> System.out.println(a);
            case "jmp" -> pc = (int) getNextInt();
            case "jt" -> pc = getBoolValue(a) ? (int) getNextInt() : pc + 1;
            case "jf" -> pc = !getBoolValue(a) ? (int) getNextInt() : pc + 1;
            case "boolAndWithVar" -> a = getBool(getBoolValue(a) && getNextBool());
            case "boolOrWithVar" -> a = getBool(getBoolValue(a) || getNextBool());
            case "boolEqualsWithVar" -> a = getBool(a.equals(getNextVarValue()));
            case "largerThanOrEqualWithVar" -> a = getBool(getIntValue(a) >= getIntValue(getNextVarValue()));
            case "smallerThanOrEqualWithVar" -> a = getBool(getIntValue(a) <= getIntValue(getNextVarValue()));
            case "boolNotEqualsWithVar" -> a = getBool(!a.equals(getNextVarValue()));
            case "smallerThanWithVar" -> a = getBool(getIntValue(a) < getIntValue(getNextVarValue()));
            case "largerThanWithVar" -> a = getBool(getIntValue(a) > getIntValue(getNextVarValue()));
            case "putPixel" -> buffer.addAll(List.of("putPixel", getNextVarValue(), getNextVarValue()));
            case "putLine" -> {
                String x0 = getNextVarValue(), y0 = getNextVarValue(), x1 = getNextVarValue(), y1 = getNextVarValue();
                //System.out.println("Draw line from " + x0 + ", " + y0 + " to " + x1 + ", " + y1);
                buffer.addAll(List.of("drawLine", x0, y0, x1, y1));
            }
            case "putRect" -> buffer.addAll(List.of("fillRect", getNextVarValue(), getNextVarValue(), getNextVarValue(), getNextVarValue()));
            case "setColor" -> {
                String color = getNextVarValue();
                //System.out.println("setColor " + color);
                buffer.addAll(List.of("setColor", color));
            }
            case "clg" -> buffer.clear();
            case "done" -> running = false;
            case "malloc" -> a = String.valueOf(allocateMemory((int) getNextIntVar()));
            case "round" -> {
                double toRound = getIntValue(getNextVarValue());
                double digits = getIntValue(getNextVarValue());

                a = getInt(Math.round(toRound * Math.pow(10, digits)) / Math.pow(10, digits));
            }
            case "floor" -> {
                double toRound = getIntValue(getNextVarValue());
                double digits = getIntValue(getNextVarValue());

                a = getInt(Math.floor(toRound * Math.pow(10, digits)) / Math.pow(10, digits));
            }
            case "ceil" -> {
                double toRound = getIntValue(getNextVarValue());
                double digits = getIntValue(getNextVarValue());

                a = getInt(Math.ceil(toRound * Math.pow(10, digits)) / Math.pow(10, digits));
            }
            case "cos" -> a = getInt(Math.cos(getIntValue(getNextVarValue())));
            case "sin" -> a = getInt(Math.sin(getIntValue(getNextVarValue())));
            case "sqrt" -> a = getInt(Math.sqrt(getIntValue(getNextVarValue())));
            case "atan2" -> a = getInt(Math.atan2(getIntValue(getNextVarValue()), getIntValue(getNextVarValue())));
            case "mouseDown" -> a = getBool(isMouseDown);
            case "mouseX" -> a = getInt(MouseInfo.getPointerInfo().getLocation().x);
            case "mouseY" -> a = getInt(MouseInfo.getPointerInfo().getLocation().y);
            case "sleep" -> {
                try {
                    Thread.sleep((long) getIntValue(getNextVarValue()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            case "drawText" -> buffer.addAll(List.of("writeString", getNextVarValue()));
            case "loadAtVarWithOffset" -> a = ram[(int) (getVar(getNext()) + getIntValue(getNextVarValue()))];
            case "storeAtVarWithOffset" -> ram[(int) (getVar(getNext()) + getIntValue(getNextVarValue()))] = a;
            case "isKeyPressed" -> a = getBool(keysPressed.contains(mapKey(getNextVarValue())));
            case "createArray" -> {}
            case "createColor" -> a = String.valueOf(new Color((int) getNextIntVar(), (int) getNextIntVar(), (int) getNextIntVar()).getRGB());
            case "charAt" -> a = getNextVarValue().charAt((int) getNextIntVar()) + "";
            case "contains" -> a = getBool(getNextVarValue().contains(getNextVarValue()));
            case "join" -> a = a + getNextVarValue();
            case "setStrokeWidth" -> buffer.addAll(List.of("setStroke", getNextVarValue()));
            case "inc" -> {
                int addr = getVar(getNext());
                ram[addr] = getInt(getIntValue(ram[addr]) + 1);
            }
            case "dec" -> {
                int addr = getVar(getNext());
                ram[addr] = getInt(getIntValue(ram[addr]) - 1);
            }
            case "arraySize" -> {}
            case "graphicsFlip" -> {
                graphics.process(buffer, image.createGraphics());
                panel.repaint();
            }
            case "newLine" -> buffer.add("cr");
            case "ask" -> {
                System.out.print(getNextVarValue() + "? ");
                a = new Scanner(System.in).nextLine();
            }
            case "setCloudVar" -> {}
            case "getCloudVar" -> {}
            case "indexOfChar" -> {}
            case "goto" -> buffer.addAll(List.of("goto", getNextVarValue(), getNextVarValue()));
            case "imalloc" -> a = getInt(allocateMemory((int) getNextInt()));
            case "getValueAtPointer" -> a = ram[(int) getNextIntVar()];
            case "setValueAtPointer" -> ram[(int) getNextIntVar()] = a;
            case "typeOf" -> {}
            case "runtimeMillis" -> a = getInt(System.currentTimeMillis() - runStart);
            case "free" -> {
                int addr = (int) getNextIntVar();
                int words = (int) getNextIntVar();

                for (int i = addr; i < addr + words; i++)
                    usedAddresses[i] = false;
            }
            case "nop" -> {}
            default -> throw new VMException("Unknown instruction '" + instruction + "'");
        }
    }
}
