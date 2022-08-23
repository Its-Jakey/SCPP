package ide;

import compiler.Compiler;
import compiler.Console;
import ide.project.ProjectSelector;
import ide.syntax.SyntaxListener;
import slvm.SLVM;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;

public class Ide extends JFrame {
    public static final String configPath = System.getProperty("user.home") + "/.scppide/config.txt";
    JTabbedPane tabs;
    JTextArea console;
    private final PrintStream outputStream;
    private FileTree fileHierarchy;
    private Config config;
    private Config projectConfig;
    String projectDir;
    Font font;

    public static Config getDefaultConfig() {
        Config ret = new Config();
        ret.addOption("font", "Consolas", "bold", "12");

        File projectRoot = new File(System.getProperty("user.home") + "/SCPPProjects");
        if (!projectRoot.exists())
            projectRoot.mkdir();

        ret.addOption("projectRoot", projectRoot.getAbsolutePath());

        new File(configPath.substring(0, configPath.lastIndexOf('/'))).mkdir();
        ret.writeToFile(Path.of(configPath));
        return ret;
    }

    public CodeEditor getCurrentTab() {
        return (CodeEditor) tabs.getSelectedComponent();
    }

    private void updateConfig() {
        //config.editOption("font", font.getFamily(), font.isBold() ? "bold" : "plain", String.valueOf(font.getSize()));
        projectConfig.editOption("tabs", Arrays.stream(tabs.getComponents()).map(c -> ((CodeEditor) c).path).filter(Objects::nonNull).toArray(String[]::new));
        config.editOption("currentProject", projectDir);
    }

    public static Config getDefaultProjectConfig() {
        Config ret = new Config();
        ret.addOption("tabs", new String[0]);
        ret.addOption("new");
        return ret;
    }

    private void loadProjectConfigSettings() {
        for (String s : projectConfig.getOption("tabs")) {
            CodeEditor ce = new CodeEditor(font, s, functionListener);
            SyntaxListener.applySyntax(ce.p);
            tabs.addTab(new File(s).getName().substring(0, new File(s).getName().lastIndexOf('.')), ce);
        }
        ((Menu) super.getJMenuBar()).update();
    }

    KeyListener functionListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_F5 -> runCode(getCurrentTab());
                case KeyEvent.VK_F1 -> console.setText("");
                case KeyEvent.VK_F2 -> {
                    if (tabs.getComponents().length > 0) tabs.remove(tabs.getSelectedIndex());
                }
                default -> {
                    if (e.isControlDown())
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_S -> getCurrentTab().save();
                            case KeyEvent.VK_O -> {
                                if (e.isShiftDown()) {
                                    try {
                                        createProperSubInstance();
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                                else
                                    openFile();
                            }
                            case KeyEvent.VK_N -> tabs.add("*", new CodeEditor(font, functionListener));
                            case KeyEvent.VK_X -> {
                                String text = getCurrentTab().p.getSelectedText();
                                if (text != null) {
                                    getCurrentTab().p.replaceSelection("");
                                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
                                }
                            }
                            case KeyEvent.VK_C -> {
                                String text = getCurrentTab().p.getSelectedText();
                                if (text != null)
                                    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(text), null);
                            }
                            case KeyEvent.VK_V -> {
                                Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
                                if (t.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                                    try {
                                        String text = t.getTransferData(DataFlavor.stringFlavor).toString();
                                        JTextPane p = getCurrentTab().p;

                                        if (p.getSelectedText() != null) {
                                            p.replaceSelection(text);
                                        } else {
                                            int caretPosition = p.getCaretPosition();
                                            StringBuilder tmp = new StringBuilder(p.getText());
                                            tmp.insert(caretPosition, text);
                                            p.setText(tmp.toString());
                                            p.setCaretPosition(caretPosition + text.length());
                                        }
                                    } catch (UnsupportedFlavorException | IOException ex) {
                                        throw new RuntimeException(ex);
                                    }
                                }
                            }
                        }
                }
            }
        }
    };

    public static Ide createProperInstance() throws IOException {
        Config config;

        if (new File(configPath).exists())
            config = Config.fromFile(configPath);
        else
            config = getDefaultConfig();
        String projectDir = config.hasOption("currentProject") && new File(config.getOption("currentProject")[0]).exists() ? config.getOption("currentProject")[0] : ProjectSelector.getProject(config);

        if (projectDir == null)
            return null;
        Ide ide = new Ide(projectDir, config);
        ide.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ide.setVisible(true);

        return ide;
    }

    public static Ide createProperSubInstance() throws IOException {
        Config config;

        if (new File(configPath).exists())
            config = Config.fromFile(configPath);
        else
            config = getDefaultConfig();
        String projectDir = ProjectSelector.getProject(config);

        if (projectDir == null)
            return null;
        Ide ide = new Ide(projectDir, config);
        ide.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ide.setVisible(true);

        return ide;
    }

    public void openFile() {
        JFrame f2 = new JFrame();
        FileDialog fd = new FileDialog(f2, "Select File To Open", FileDialog.LOAD);
        fd.setVisible(true);

        if(new File(fd.getDirectory() + "/" + fd.getFile()).exists()) {
            tabs.add(fd.getFile(), new CodeEditor(font, fd.getDirectory() + "/" + fd.getFile(), functionListener));
            tabs.setSelectedIndex(tabs.getTabCount() - 1);
            ((Menu) super.getJMenuBar()).update();
        }
    }

    public void runCode(CodeEditor ce) {
        console.setText("");

        new Thread(() -> {
            try {
                String asm = Compiler.compile(Path.of(getRealPath(ce.path)));
                new SLVM(asm).run();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }).start();
    }

    public static String getValidProject() {
        while (true) {
            FileDialog fd = new FileDialog((Frame) null, "Open Project", FileDialog.LOAD);
            fd.setVisible(true);

            if (fd.getName() != null) {
                File path = new File(fd.getDirectory() + "/" + fd.getFile());

                if (new File(path.getPath() + "/config.txt").exists())
                    return path.getPath();
                else
                    JOptionPane.showMessageDialog(null, "Invalid project directory");
            } else
                return null;
        }
    }

    private void buildUI() {
        setSize(1200, 950);
        setLayout(null);
        setJMenuBar(new Menu(this));

        tabs = new JTabbedPane();
        tabs.setBounds(200, 0, getWidth() - 200, getHeight() - 200);
        tabs.addChangeListener(e -> ((Menu) super.getJMenuBar()).update());

        add(tabs);

        console = new JTextArea();
        console.setFont(font);
        console.setEditable(true);
        console.setBounds(0, getHeight() - 200, getWidth(), 200);
        add(console);

        fileHierarchy = new FileTree(new File(projectDir), this);
        fileHierarchy.setBounds(0, 0, 200, getHeight() - 200);
        add(fileHierarchy);
    }

    private void openProject() throws IOException {
        projectConfig = Config.fromFile(projectDir + "/config.txt");
        font = new Font("monospaced", Font.PLAIN, 12);

        buildUI();
        loadProjectConfigSettings();
        updateConfig();
    }

    public Ide(String projectDir, Config config) throws IOException {
        this.projectDir = projectDir;
        this.config = config;

        openProject();


        outputStream = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {
                console.append(String.valueOf((char) b));
            }
        });

        Console.out = outputStream;
        Console.err = Console.out;
    }

    private void addListeners() {
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                updateConfig();
                config.writeToFile(Path.of(configPath));
                projectConfig.writeToFile(Path.of(projectDir + "/config.txt"));
                Arrays.stream(tabs.getComponents()).map(component -> (CodeEditor) component).forEach(CodeEditor::save);
            }
        });

        super.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                tabs.setBounds(200, 0, getWidth(), getHeight() - 200);
                console.setBounds(0, getHeight() - 200, getWidth(), 200);
                fileHierarchy.setBounds(0, 0, 200, getHeight() - 200);
            }
        });

        Timer timer = new Timer(1000, e -> {
            if (getCurrentTab() != null)
                SyntaxListener.applySyntax(getCurrentTab().p);
        });
        timer.start();
    }

    public PrintStream getOutputStream() {
        return outputStream;
    }

    public static String createFilepath() {
        FileDialog fd = new FileDialog((Frame) null, "Filepath", FileDialog.SAVE);
        fd.setVisible(true);
        return fd.getDirectory() + "/" + fd.getFile();
    }

    public static String getRealPath(String path) {
        if (path == null)
            return createFilepath();
        return path;
    }
}
