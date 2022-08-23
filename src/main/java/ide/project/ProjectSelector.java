package ide.project;

import ide.Config;
import ide.Ide;
import org.apache.commons.lang3.ArrayUtils;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collector;

public class ProjectSelector extends javax.swing.JFrame {
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton newBtn;
    private javax.swing.JButton openBtn;
    private javax.swing.JList<String> projectList;

    private String projectDir;
    private final Config config;

    public ProjectSelector(Config config) {
        this.config = config;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        projectList = new javax.swing.JList<>();
        newBtn = new javax.swing.JButton();
        openBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        projectList.setModel(new javax.swing.AbstractListModel<>() {
            String[] strings = Arrays.stream(new File(config.getOption("projectRoot")[0]).listFiles()).map(File::getName).toArray(String[]::new);

            public int getSize() {
                return strings.length;
            }

            public String getElementAt(int i) {
                return strings[i];
            }
        });
        projectList.addListSelectionListener(this::projectListValueChanged);
        jScrollPane1.setViewportView(projectList);

        newBtn.setText("New Project");
        newBtn.addActionListener(this::newBtnActionPerformed);

        openBtn.setText("Open Project");
        openBtn.addActionListener(this::openBtnActionPerformed);

        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(this::cancelBtnActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cancelBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(newBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(openBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelBtn)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newBtnActionPerformed(java.awt.event.ActionEvent evt) {
        String name = JOptionPane.showInputDialog(null, "Project Name");
        if (name == null) {
            dispose();
            return;
        }
        String projectRoot = config.getOption("projectRoot")[0];

        if (new File(projectRoot).listFiles() != null && Arrays.stream(new File(projectRoot).listFiles()).anyMatch(f -> f.getName().equals(name))) {
            JOptionPane.showMessageDialog(null, "Project already exists");
            return;
        }

        projectDir = config.getOption("projectRoot")[0] + "/" + name + "/";
        new File(projectDir).mkdir();

        Config projectConfig = Ide.getDefaultProjectConfig();
        projectConfig.editOption("tabs", projectDir + "/main.sc");
        projectConfig.writeToFile(Path.of(projectDir + "config.txt"));
        try {
            Files.writeString(Path.of(projectDir + "main.sc"),
                    Arrays.stream(ArrayUtils.toObject(getClass().getResourceAsStream("/demo.sc")
                            .readAllBytes())).map(b -> (char) (b + 0))
                            .collect(Collector.of(
                                    StringBuilder::new,
                                    StringBuilder::append,
                                    StringBuilder::append,
                                    StringBuilder::toString)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        dispose();
    }

    private void openBtnActionPerformed(java.awt.event.ActionEvent evt) {
        projectDir = Ide.getValidProject();
        dispose();
    }

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }

    private void projectListValueChanged(javax.swing.event.ListSelectionEvent evt) {
        projectDir = config.getOption("projectRoot")[0] + "/" + projectList.getSelectedValue();
        dispose();
    }

    public static String getProject(Config config) {
        ProjectSelector selector = new ProjectSelector(config);
        selector.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        selector.setTitle("Open Project");
        selector.setVisible(true);

        while (selector.projectDir == null) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return selector.projectDir;
    }
}
