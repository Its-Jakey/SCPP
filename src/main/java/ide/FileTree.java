package ide;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;
import java.util.Vector;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

/**
 * Display a file system in a JTree view
 *
 * @version $Id: FileTree.java,v 1.9 2004/02/23 03:39:22 ian Exp $
 * @author Ian Darwin
 */
public class FileTree extends JPanel {
    private final File dir;
    private final JTree tree;
    private final JScrollPane scrollPane;
    private TreeSelectionListener listener;

    /** Construct a FileTree */
    public FileTree(File dir, Ide i) {
        this.dir = dir;
        setLayout(new BorderLayout());

        // Make a tree list with all the nodes, and make it a JTree
        tree = new JTree(addNodes(null, dir));

        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int selRow = tree.getRowForLocation(e.getX(), e.getY());
                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) Objects.requireNonNull(selPath).getLastPathComponent();

                if (SwingUtilities.isRightMouseButton(e)){
                    tree.setSelectionPath(selPath);
                    if (selRow >- 1){
                        tree.setSelectionRow(selRow);
                    }
                } else if (e.getClickCount() == 2) {
                    String path = dir.getAbsolutePath() + "/" + node.getUserObject().toString();

                    if (!new File(path).isDirectory() && Arrays.stream(i.tabs.getComponents()).noneMatch(c -> ((CodeEditor) c).path.equals(path)))
                        i.tabs.add(node.getUserObject().toString(), new CodeEditor(i.font, path, i.functionListener));
                }

            }
        };
        tree.addMouseListener(ml);

        // Lastly, put the JTree into a JScrollPane.
        scrollPane = new JScrollPane();
        scrollPane.getViewport().add(tree);
        add(BorderLayout.CENTER, scrollPane);
    }

    public void refresh() {
        scrollPane.removeAll();
        JTree newTree = new JTree(addNodes(null, dir));
        newTree.addTreeSelectionListener(listener);
        scrollPane.add(newTree);
    }

    /** Add nodes from under "dir" into curTop. Highly recursive. */
    DefaultMutableTreeNode addNodes(DefaultMutableTreeNode curTop, File dir) {
        String curPath = dir.getPath();
        DefaultMutableTreeNode curDir = new DefaultMutableTreeNode(curPath);

        if (curTop != null) { // should only be null at root
            curTop.add(curDir);
        }
        Vector<String> ol = new Vector<>();
        String[] tmp = dir.list();

        if (tmp != null)
            for (String s : tmp) ol.addElement(s);
        ol.sort(String.CASE_INSENSITIVE_ORDER);
        File f;
        Vector<String> files = new Vector<>();
        // Make two passes, one for Dirs and one for Files. This is #1.
        for (int i = 0; i < ol.size(); i++) {
            String thisObject = ol.elementAt(i);
            String newPath;
            if (curPath.equals("."))
                newPath = thisObject;
            else
                newPath = curPath + File.separator + thisObject;
            if ((f = new File(newPath)).isDirectory())
                addNodes(curDir, f);
            else
                files.addElement(thisObject);
        }
        // Pass two: for files.
        for (int fnum = 0; fnum < files.size(); fnum++)
            curDir.add(new DefaultMutableTreeNode(files.elementAt(fnum)));
        return curDir;
    }

    public Dimension getMinimumSize() {
        return new Dimension(200, 400);
    }

    public Dimension getPreferredSize() {
        return new Dimension(200, 400);
    }
}