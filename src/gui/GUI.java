package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;

import mvc.FileSetsModel;
import mvc.SetController;
import mvc.SetControllerThread;
import mvc.SetView;
import parser.Parser;

public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private static GUI gui;
    Dimension screenSize;

    private static JTabbedPane tabbedPane;
    private static JTextField eval_textfield;
    private static JTextArea result_TextArea;
    private static JLabel bottom_progessBar;

//	private static String exportPath = "./Export";

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    private String open() throws NoSuchAlgorithmException, IOException {
        final JFileChooser chooser = new JFileChooser("Verzeichnis wählen");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setFileHidingEnabled(false);
        final File file = new File("~/");

        chooser.setCurrentDirectory(file);

        chooser.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                if (e.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
                        || e.getPropertyName().equals(JFileChooser.DIRECTORY_CHANGED_PROPERTY)) {
                    final File f = (File) e.getNewValue();
                }
            }
        });

        chooser.setVisible(true);
        final int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File inputVerzFile = chooser.getSelectedFile();
            String inputVerzStr = inputVerzFile.getPath();

            return inputVerzStr;
        }

        chooser.setVisible(false);

        return null;
    }

    private GUI() {
        super();

        ToolTipManager.sharedInstance().setDismissDelay(10000);

        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width / 2, screenSize.height / 2);

        Container pane = this.getContentPane();
        /*
         * Upper part
         */
        JPanel ceiling_pane = new JPanel();
        ceiling_pane.setLayout(new BoxLayout(ceiling_pane, BoxLayout.LINE_AXIS));
        pane.add(ceiling_pane, BorderLayout.PAGE_START);

        JButton button_open = new JButton("open");
        ceiling_pane.add(button_open);
        button_open.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // your actions
                SwingUtilities.invokeLater(() -> {

                    try {

                        String path = open();

                        // Create new Tab
                        // set tabs name to name of new set
                        String name = name(FileSetsModel.getInstance().getList().size());
                        addTab(name);

                        // select new tab
                        tabbedPane.setSelectedIndex(tabbedPane.getComponentCount() - 1);

                        // set text on tab
                        int count = tabbedPane.getTabCount();
                        JScrollPane scrollPane = (JScrollPane) tabbedPane.getComponent(count - 1);
                        JViewport viewport = scrollPane.getViewport();
                        JTextArea textArea = (JTextArea) viewport.getView();
                        textArea.setText(path);

                        // initializing mvc

                        SetController setController = new SetController();

                        // set name of new set
                        setController.getSetModel().setName(name);

                        SetView setView = new SetView(textArea);

                        setView.subscribe(setController.getSetModel());
                        setController.getSetModel().addView(setView);

                        // set new list causes update()
                        List<SetController> list = FileSetsModel.getInstance().getList();
                        list.add(setController);
                        FileSetsModel.getInstance().setList(list);

                        // analyse path to new created tab
                        // TODO analyze in a thread
                        Thread thread = new Thread(new SetControllerThread(setController, path));

                        thread.start();

                    } catch (NoSuchAlgorithmException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }

                });
            }

            private void addTab(String name) {
                // TODO Auto-generated method stub
                JTextArea tabbedPane_TextArea = new JTextArea();
                JScrollPane set_ScrollPane = new JScrollPane(tabbedPane_TextArea);

                ImageIcon icon = createImageIcon("images/middle.gif", "");

                tabbedPane.addTab(name, icon, set_ScrollPane, "Does nothing");
                tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
            }

            private String name(int id) {
                /*
                 * 0 => A 1 => B ... 25 => Z 26 => AA 27 => BB ... 51 = ZZZ 52 => AAA ...
                 */
                char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q',
                        'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
                StringBuilder name = new StringBuilder("");
                char c = alphabet[(id % 26)];
                name.append(c);
                for (int i = 0; i < (id / 26); ++i) {
                    name.append(c);
                }
                return name.toString();
            }

        });

        ceiling_pane.add(new JSeparator(SwingConstants.VERTICAL));

        JPanel ceiling_eval = new JPanel(new java.awt.BorderLayout());
        ceiling_pane.add(ceiling_eval, BorderLayout.PAGE_START);
        ceiling_eval.setPreferredSize(new Dimension(250, 30));

        eval_textfield = new JTextField();
        eval_textfield.setToolTipText("<html>Hier können Sie einen logischen Ausdruck eingeben.<br>" +
                "Mengenbezeichner sind A, B, C etc.<br>" +
                "Operatoren sind & | - /.<br>" +
                "Achten Sie darauf, dass A&B nicht funktioniert aber A & B schon.</html>");
        ceiling_eval.add(eval_textfield, BorderLayout.CENTER);

        JButton eval_button = new JButton("evaluate");
        ceiling_eval.add(eval_button, BorderLayout.LINE_END);
        eval_button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Get eval of the parser running

                Parser parser = new Parser(eval_textfield.getText());
                parser.scanToken();
                parser.resultTree = parser.parseS();

                if (parser.getNextToken() != "") {
                    System.err.println("Input-String fehlerhaft. Enthält: " + parser.getNextToken());
                }

                if (parser.resultTree != null) {
                    System.out.println("evaluating : " + parser.resultTree.toString());
                    SetController setController;
                    setController = parser.resultTree.eval();
                    result_TextArea.setText(setController.toString());
                }

            }

        });

        ceiling_pane.add(new JSeparator(SwingConstants.VERTICAL));

        JButton button_export = new JButton("export");
        ceiling_pane.add(button_export);

        /*
         * Center part
         */

        tabbedPane = new JTabbedPane();

        result_TextArea = new JTextArea();
        result_TextArea.setToolTipText(
                "<html>Nachdem Sie einen gültigen logischen Ausdruck oben in die Eingabezeile"
                + "<br>eingegeben haben und evaluate betätigt haben erscheint das Ergebnis der Mengenoperation "
                        + "<br>hier in diesem Textfeld.</html>");
        JScrollPane result_ScrollPane = new JScrollPane(result_TextArea);

        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, tabbedPane, result_ScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(this.getHeight() / 2);

        pane.add(splitPane);

        /*
         * Lower part
         */
        JPanel bottom = new JPanel(new java.awt.GridLayout(0, 1));
        pane.add(bottom, BorderLayout.PAGE_END);

        bottom_progessBar = new JLabel("");
        bottom.add(bottom_progessBar);

        /*
         * 	
         */
        // Create new JFrame with title "FileSets"
        this.setTitle("FileSets");

        // exit on close button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Wir lassen unseren Frame anzeigen
        this.setVisible(true);
    }

    public static JLabel getBottom_progessBar() {
        return bottom_progessBar;
    }

    public static void setBottom_progessBar(JLabel bottom_progessBar) {
        GUI.bottom_progessBar = bottom_progessBar;
    }

    public static GUI getInstance() {
        if (gui == null) {
            return gui = new GUI();
        } else {
            return gui;
        }
    }

    public static JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public static void main(String[] args) {
        GUI.getInstance();
    }

}
