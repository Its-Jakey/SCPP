package ide;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Menu extends JMenuBar {
    private final JMenuItem runBtn;
    private final Ide i;
    
    void update() {
        if (i.getCurrentTab() == null)
            return;

        if (i.getCurrentTab().path == null)
            runBtn.setEnabled(false);
        else {
            runBtn.setEnabled(true);
            runBtn.setText("Run '" + new File(i.getCurrentTab().path).getName().substring(0, new File(i.getCurrentTab().path).getName().lastIndexOf('.')) + "'");
        }
        i.tabs.setTitleAt(i.tabs.getSelectedIndex(), i.getCurrentTab().path == null ? "*" : new File(i.getCurrentTab().path).getName());
    }

    public Menu(Ide i){
        this.i = i;
        
        JMenu mnFile = new JMenu("File");
        add(mnFile);

        runBtn = new JMenuItem("Run ''");

        JMenuItem saveBtn = new JMenuItem("Save");
        saveBtn.addActionListener(e -> {
            i.getCurrentTab().save();
            update();
        });
        mnFile.add(saveBtn);

        JMenuItem saveAsBtn = new JMenuItem("Save As");
        saveAsBtn.addActionListener(e -> {
            try {
                i.getCurrentTab().path = Ide.createFilepath();
                CodeEditor.write(i.getCurrentTab().path, i.getCurrentTab().getText());

                update();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });
        mnFile.add(saveAsBtn);

        JMenuItem openBtn = new JMenuItem("Open");
        openBtn.addActionListener(e -> i.openFile());
        mnFile.add(openBtn);

        JMenuItem newBtn = new JMenuItem("New");
        newBtn.addActionListener(e -> {
            i.tabs.add("*", new CodeEditor(i.font, i.functionListener));
            i.tabs.setSelectedIndex(i.tabs.getTabCount() - 1);
            update();
        });
        mnFile.add(newBtn);

        JMenu nmRun = new JMenu("Run");
        add(nmRun);


        runBtn.addActionListener(e -> i.runCode(i.getCurrentTab()));
        runBtn.setEnabled(false);
        nmRun.add(runBtn);

    }
}
