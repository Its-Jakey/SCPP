package slvm;

import compiler.Console;
import compiler.optimizer.Optimizer;
import org.apache.commons.text.StringEscapeUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.*;

public class SLVM {
    private final String[] instructions;
    private final String[] ram = new String[0xFFFFFFF];
    private final int[] variables = new int[0xFFFFF];
    private final boolean[] usedAddresses = new boolean[ram.length];
    int pc;
    private String a;
    private boolean isMouseDown;
    private final List<Integer> keysPressed;
    private final Stack<Integer> stack;
    private final Stack<String> varStack;
    private final boolean running;
    private final List<String> buffer;
    private final VMGraphics graphics;
    private JPanel panel;
    private long runStart;
    private final BufferedImage image;
    String $fileName = null, $line = "0";
    private Point lastKnownMousePosition = new Point(0, 0);
    //static List<Metadata> metadata;

    private double getIntValue(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0;
        } catch (Exception e) {
            throw new VMException(e.getMessage(), this);
        }
    }
    private String getNext() {
        if (pc >= instructions.length)
            throw new VMException("PC out of program range", this);
        return instructions[pc++];
    }
    private double getNextInt() {
        return getIntValue(getNext());
    }
    /* private int getVar(String varStr) {
        int var = Integer.parseInt(varStr);

        if (variables[var] == -1) {
            variables[var] = allocateMemory(1);
            ram[variables[var]] = "0";
        }

        return variables[var];
    } */

    private final HashMap<String, Integer> tmpVars = new HashMap<>();
    private int getVar(String var) {
        if (!tmpVars.containsKey(var)) {
            tmpVars.put(var, allocateMemory(1));
            ram[tmpVars.get(var)] = "0";
        }
        return tmpVars.get(var);
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
        throw new VMException("Could not allocate " + words + " words because there is no free memory", this);
    }
    private String getNextVarValue() {
        return ram[getVar(getNext())];
    }

    private void setNextVarValue(String value) {
        String varName = getNext();

        if (value == null)
            throw new VMException("Program attempted to set variable '" + varName + "' to a null value", this);
        ram[getVar(varName)] = value;
    }

    private boolean getBoolValue(String value) {
        return getIntValue(value) > 0;
    }
    private boolean getNextBool() {
        return getBoolValue(getNextVarValue());
    }
    private String getBool(boolean bool) {
        return String.valueOf(bool ? 1 : 0);
    }
    private String getInt(double val) {
        if (Math.round(val) == val)
            return String.valueOf((int) val);
        return String.valueOf(val);
    }
    private double getNextIntVar() {
        return getIntValue(getNextVarValue());
    }

    private boolean isInt(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isEqual(String a, String b) {
        if (isInt(a) && isInt(b))
            return getIntValue(a) == getIntValue(b);
        return a.equals(b);
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

    private JFrame frame;
    static List<Integer> subroutines;


    public SLVM(String[] program) {
        this.instructions = Optimizer.optimize(program);
        this.running = true;
        this.buffer = new ArrayList<>();
        this.stack = new Stack<>();
        this.varStack = new Stack<>();
        this.graphics = new VMGraphics();
        this.keysPressed = new ArrayList<>();
        this.image = new BufferedImage(480, 360, BufferedImage.TYPE_INT_RGB);
        subroutines = new ArrayList<>();
        //metadata = new ArrayList<>();

        try {
            Files.writeString(Path.of("optimiedASM.slvm.txt"), String.join("\n", instructions));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Arrays.fill(variables, -1);

        SwingUtilities.invokeLater(() -> {
            frame = new JFrame();
            frame.setSize(480, 360);

            panel = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
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
        });

        Arrays.fill(ram, "0");
    }

    public void run() throws NoSuchAlgorithmException {
        //Arrays.fill(ram, "");

        runStart = System.currentTimeMillis();
        while (running)
            execute(instructions[pc++]);
    }

    private void execute(String instruction) throws NoSuchAlgorithmException {
        switch (instruction) { //TODO: Implement graphics
            case "ldi" -> a = StringEscapeUtils.unescapeJava(getNext());
            case "loadAtVar" -> a = getNextVarValue();
            case "storeAtVar" -> setNextVarValue(a);
            case "jts" -> {
                stack.push(pc + 1);
                pc = (int) getNextInt();
                subroutines.add(pc);
                //metadata.add(new Metadata(fileName, line));
            }
            case "ret" -> {
                pc = stack.pop();
                subroutines.remove(subroutines.size() - 1);
                //metadata.remove(metadata.size() - 1);
            }
            case "addWithVar" -> a = getInt(getIntValue(a) + getNextIntVar());
            case "subWithVar" -> a = getInt(getIntValue(a) - getIntValue(getNextVarValue()));
            case "mulWithVar" -> a = getInt(getIntValue(a) * getIntValue(getNextVarValue()));
            case "divWithVar" -> a = getInt(getIntValue(a) / getIntValue(getNextVarValue()));
            case "bitwiseLsfWithVar" -> a = getInt(((int) getIntValue(a)) << ((int) getIntValue(getNextVarValue())));
            case "bitwiseRsfWithVar" -> a = getInt(((int) getIntValue(a)) >> ((int) getIntValue(getNextVarValue())));
            case "bitwiseAndWithVar" -> a = getInt(((int) getIntValue(a)) & ((int) getIntValue(getNextVarValue())));
            case "bitwiseOrWithVar" -> a = getInt(((int) getIntValue(a)) | ((int) getIntValue(getNextVarValue())));
            case "bitwiseXorWithVar" -> a = getInt(((int) getIntValue(a)) ^ ((int) getIntValue(getNextVarValue())));
            case "bitwiseNot" -> a = String.valueOf(~((int)getNextIntVar()));
            case "modWithVar" -> a = getInt(getIntValue(a) % getIntValue(getNextVarValue()));
            case "print" -> Console.out.print(StringEscapeUtils.unescapeJava(a));
            case "println" -> Console.out.println(StringEscapeUtils.unescapeJava(a));
            case "jmp" -> pc = (int) getNextInt();
            case "jt" -> pc = getBoolValue(a) ? (int) getNextInt() : pc + 1;
            case "jf" -> pc = !getBoolValue(a) ? (int) getNextInt() : pc + 1;
            case "boolAndWithVar" -> {
                boolean boolA = getBoolValue(a);
                boolean boolB = getNextBool();
                a = getBool(boolA && boolB);
            }
            case "boolOrWithVar" -> {
                boolean boolA = getBoolValue(a);
                boolean boolB = getNextBool();
                a = getBool(boolA || boolB);
            }
            case "boolEqualWithVar" -> a = getBool(isEqual(a, getNextVarValue()));
            case "largerThanOrEqualWithVar" -> a = getBool(getIntValue(a) >= getIntValue(getNextVarValue()));
            case "smallerThanOrEqualWithVar" -> a = getBool(getIntValue(a) <= getIntValue(getNextVarValue()));
            case "boolNotEqualWithVar" -> a = getBool(!isEqual(a, getNextVarValue()));
            case "smallerThanWithVar" -> a = getBool(getIntValue(a) < getIntValue(getNextVarValue()));
            case "largerThanWithVar" -> a = getBool(getIntValue(a) > getIntValue(getNextVarValue()));
            case "putPixel" -> buffer.addAll(List.of("putPixel", getNextVarValue(), getNextVarValue()));
            case "putLine" -> {
                String x0 = getNextVarValue(), y0 = getNextVarValue(), x1 = getNextVarValue(), y1 = getNextVarValue();
                //Console.out.println("Draw line from " + x0 + ", " + y0 + " to " + x1 + ", " + y1);
                buffer.addAll(List.of("drawLine", x0, y0, x1, y1));
            }
            case "putRect" -> buffer.addAll(List.of("fillRect", getNextVarValue(), getNextVarValue(), getNextVarValue(), getNextVarValue()));
            case "setColor" -> {
                String color = getNextVarValue();
                //Console.out.println("setColor " + color);
                buffer.addAll(List.of("setColor", color));
            }
            case "clg" -> buffer.clear();
            case "done" -> Thread.currentThread().stop();
            case "malloc" -> a = String.valueOf(allocateMemory((int) getNextIntVar()));
            case "round" -> {
                double toRound = getIntValue(getNextVarValue());
                double digits = getIntValue(getNextVarValue());

                if (digits == 0)
                    a = getInt(Math.round(toRound));
                else
                    a = getInt(Math.round(toRound * Math.pow(10, digits)) / Math.pow(10, digits));
            }
            case "floor" -> {
                double toRound = getIntValue(getNextVarValue());
                double digits = getIntValue(getNextVarValue());

                if (digits == 0)
                    a = getInt(Math.floor(toRound));
                else
                    a = getInt(Math.floor(toRound * Math.pow(10, digits)) / Math.pow(10, digits));
            }
            case "ceil" -> {
                double toRound = getIntValue(getNextVarValue());
                double digits = getIntValue(getNextVarValue());

                if (digits == 0)
                    a = getInt(Math.ceil(toRound));
                else
                    a = getInt(Math.ceil(toRound * Math.pow(10, digits)) / Math.pow(10, digits));
            }
            case "cos" -> a = getInt(Math.cos(getIntValue(getNextVarValue())));
            case "sin" -> a = getInt(Math.sin(getIntValue(getNextVarValue())));
            case "sqrt" -> a = getInt(Math.sqrt(getIntValue(getNextVarValue())));
            case "atan2" -> a = getInt(Math.atan2(getIntValue(getNextVarValue()), getIntValue(getNextVarValue())));
            case "mouseDown" -> a = getBool(isMouseDown);
            case "mouseX" -> {
                Point pos = (panel.getMousePosition() == null ? lastKnownMousePosition : panel.getMousePosition());
                lastKnownMousePosition = pos;
                a = getInt(pos.x);
            }
            case "mouseY" -> {
                Point pos = (panel.getMousePosition() == null ? lastKnownMousePosition : panel.getMousePosition());
                lastKnownMousePosition = pos;
                a = getInt(pos.y);
            }
            case "sleep" -> {
                try {
                    Thread.sleep((long) getIntValue(getNextVarValue()));
                } catch (IllegalArgumentException ignored){}
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            case "drawText" -> buffer.addAll(List.of("writeString", getNextVarValue()));
            case "loadAtVarWithOffset" -> a = ram[(int) (getVar(getNext()) + getIntValue(getNextVarValue()))];
            case "storeAtVarWithOffset" -> ram[(int) (getVar(getNext()) + getIntValue(getNextVarValue()))] = a;
            case "isKeyPressed" -> a = getBool(keysPressed.contains(mapKey(getNextVarValue())));
            case "createColor" -> a = String.valueOf(new Color((int) getNextIntVar(), (int) getNextIntVar(), (int) getNextIntVar()).getRGB());
            case "charAt" -> {
                String str = getNextVarValue();
                int charAt = (int) getNextIntVar();

                if (charAt >= str.length())
                    a = "";
                else
                    a = str.charAt(charAt) + "";
            }
            case "sizeOf" -> a = getInt(getNextVarValue().length());
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
            case "graphicsFlip" -> {
                frame.setVisible(true);
                graphics.process(buffer, image.createGraphics(), this);
                panel.repaint();
            }
            case "newLine" -> buffer.add("cr");
            case "ask" -> {
                Console.out.print(getNextVarValue());
                a = new Scanner(System.in).nextLine();
            }
            case "setCloudVar", "getCloudVar" -> {}
            case "indexOfChar" -> a = String.valueOf(getNextVarValue().indexOf(getNextVarValue().charAt(0)));
            case "goto" -> buffer.addAll(List.of("goto", getNextVarValue(), getNextVarValue()));
            case "imalloc" -> {
                int words = (int) getNextInt();

                a = getInt(allocateMemory(words));
            }
            case "getValueAtPointer" -> a = ram[(int) getNextIntVar()];
            case "setValueAtPointer" -> ram[(int) getNextIntVar()] = a;
            case "runtimeMillis" -> a = getInt(System.currentTimeMillis() - runStart);
            case "free" -> {
                int addr = (int) getNextIntVar();
                int words = (int) getNextIntVar();

                for (int i = addr; i < addr + words; i++)
                    usedAddresses[i] = false;
            }
            case "nop" -> {}
            case "getVarAddress" -> getVar(getNext());
            case "setVarAddress" -> {
                int var = Integer.parseInt(getNext());
                variables[var] = (int) getNextIntVar();
            }
            case "copyVar" -> {
                String source = getNext();
                String dest = getNext();

                ram[getVar(dest)] = ram[getVar(source)];
            }
            case "incA" -> a = String.valueOf(getIntValue(a) + 1);
            case "decA" -> a = String.valueOf(getIntValue(a) - 1);
            case "arrayBoundsCheck" -> { //array, a: index
                String arrayName = getNext();
                int array = (int) getIntValue(ram[getVar(arrayName)]);
                //Console.out.println(ram[array]);
                int index = (int) getIntValue(a);
                int arraySize = (int) getIntValue(ram[array - 1]);

                if (ram[array - 1] == null)
                    Console.out.println("Array size is null for array '" + arrayName + "'");

                if (index < -1 || index >= arraySize)
                    throw new VMException("Array index " + index + " out of bounds for length " + arraySize, this);
            }
            case "stackPushA" -> varStack.push(a);
            case "stackPopA" -> a = varStack.pop();
            case "stackPush" -> varStack.push(getNextVarValue());
            case "stackPop" -> ram[getVar(getNext())] = varStack.pop();
            case "stackPeekA" -> a = varStack.peek();
            case "stackPeek" -> ram[getVar(getNext())] = varStack.peek();
            case "stackInc" -> varStack.push(String.valueOf(getIntValue(varStack.pop()) + 1));
            case "stackDec" -> varStack.push(String.valueOf(getIntValue(varStack.pop()) - 1));
            case "stackAdd" -> varStack.push(String.valueOf(getIntValue(varStack.pop()) + getIntValue(varStack.pop())));
            case "stackSub" -> varStack.push(String.valueOf(getIntValue(varStack.pop()) - getIntValue(varStack.pop())));
            case "stackMul" -> varStack.push(String.valueOf(getIntValue(varStack.pop()) * getIntValue(varStack.pop())));
            case "stackDiv" -> varStack.push(String.valueOf(getIntValue(varStack.pop()) / getIntValue(varStack.pop())));
            case "stackBitwiseLsf" -> varStack.push(String.valueOf((int) getIntValue(varStack.pop()) << (int) getIntValue(varStack.pop())));
            case "stackBitwiseRsf" -> varStack.push(String.valueOf((int) getIntValue(varStack.pop()) >> (int) getIntValue(varStack.pop())));
            case "stackBitwiseAnd" -> varStack.push(String.valueOf((int) getIntValue(varStack.pop()) & (int) getIntValue(varStack.pop())));
            case "stackBitwiseOr" ->  varStack.push(String.valueOf((int) getIntValue(varStack.pop()) | (int) getIntValue(varStack.pop())));
            case "stackMod" -> varStack.push(String.valueOf(getIntValue(varStack.pop()) % getIntValue(varStack.pop())));
            case "stackBoolAnd" -> {
                boolean boolA = getIntValue(varStack.pop()) > 0;
                boolean boolB = getIntValue(varStack.pop()) > 0;

                varStack.push(boolA && boolB ? "1" : "0");
            }
            case "stackBoolOr" -> {
                boolean boolA = getIntValue(varStack.pop()) > 0;
                boolean boolB = getIntValue(varStack.pop()) > 0;

                varStack.push(boolA || boolB ? "1" : "0");
            }
            case "stackBoolEqual" -> varStack.push(varStack.pop().equals(varStack.pop()) ? "1" : "0");
            case "stackLargerThanOrEqual" -> varStack.push(getIntValue(varStack.pop()) >= getIntValue(varStack.pop()) ? "1" : "0");
            case "stackSmallerThanOrEqual" -> varStack.push(getIntValue(varStack.pop()) <= getIntValue(varStack.pop()) ? "1" : "0");
            case "stackNotEqual" -> varStack.push(varStack.pop().equals(varStack.pop()) ? "0" : "1");
            case "stackSmallerThan" -> varStack.push(getIntValue(varStack.pop()) < getIntValue(varStack.pop()) ? "1" : "0");
            case "stackLargerThan" -> varStack.push(getIntValue(varStack.pop()) > getIntValue(varStack.pop()) ? "1" : "0");
            case "usage" -> {
                int used = 0;
                for (boolean usedAddress : usedAddresses)
                    if (usedAddress)
                        used++;

                a = String.valueOf(used);
            }
            case "conditionalValueSet" -> {
                boolean condition = getBoolValue(a);
                String trueValue = getNextVarValue();
                String falseValue = getNextVarValue();

                if (condition)
                    a = trueValue;
                else
                    a = falseValue;
            }
            case "toAsciiValue" -> a = String.valueOf((int) getNextVarValue().charAt(0));
            case "fromAsciiValue" -> a = String.valueOf((char) getNextIntVar());
            case "$sha" -> a = bytesToHex(MessageDigest.getInstance("SHA-256").digest(getNextVarValue().getBytes(StandardCharsets.UTF_8)));
            case "random" -> a = String.valueOf(Math.random());
            default -> throw new VMException("Unknown instruction '" + instruction + "'", this);
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
