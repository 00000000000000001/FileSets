package classes;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import parser.Parser;

public class FileSetsForm extends JFrame {
	private static final long serialVersionUID = 1L;
	public static FileSetsControllerIF fileSetsController;
	JButton bOpen = new JButton("open");
	JButton bCalc = new JButton("calc");
	JButton bExport = new JButton("export");
	public static JTextArea lMengen = new JTextArea();
	public static JTextArea lMenge = new JTextArea();
	public static JTextArea lErgebnis = new JTextArea();
	JScrollPane spMengen = new JScrollPane(lMengen);
	JScrollPane spMenge = new JScrollPane(lMenge);
	JScrollPane spErgebnis = new JScrollPane(lErgebnis);
	static JTextField tfExpression = new JTextField("A + B", 15);
	
	public FileSetsForm() throws HeadlessException {
		super();
		// open button opens a file or foldern dialog
		bOpen.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
		    	SwingUtilities.invokeLater(() -> {
					try {
						oeffnen();
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				});
		    }


		});
		
		// TODO Config calc button
		bCalc.addActionListener(new ActionListener() {

		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // TODO Syntactic check of the expression 
		    	// TODO Get eval of the parser running
		    	Parser parser = new Parser(tfExpression.getText());
		    	parser.scanToken();
				parser.resultTree = parser.parseS();
				lErgebnis.setText(parser.resultTree.eval().toString());
		    	System.out.println(parser.resultTree.toString());
		    }


		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		// open button
		panel.add(bOpen);
		// Mengen View
		panel.add(spMengen);
		// Menge View
		panel.add(spMenge);
		// Textfield for expression
		panel.add(tfExpression);
		// Add calcbutton to form
		panel.add(bCalc);
		// Textarea zum anzeigen der Ergebnismenge
		panel.add(spErgebnis);
		// open button
		panel.add(bExport);
		
		spMengen.setPreferredSize(new Dimension(50, 200));
		spMenge.setPreferredSize(new Dimension(500, 200));
		spErgebnis.setPreferredSize(new Dimension(500, 200));
		
		fileSetsController = new FileSetsController();
		
		// Create new JFrame with title "FileSets"
		this.setTitle("FileSets");
		
		this.add(panel);
		
		
		// exit on close button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* Wir setzen die Breite und die Höhe 
        unseres Fensters auf 200 Pixel */        
		this.setSize(1200,500);
     	// Wir lassen unseren Frame anzeigen
		this.setVisible(true);
		
	}
	
	
	private void oeffnen() throws NoSuchAlgorithmException, IOException {
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
            
            System.out.println("Eingabepfad:" + inputVerzStr);
            
            fileSetsController.create(inputVerzStr);
        }
        System.out.println("Fertig");
        chooser.setVisible(false);
    } 


	public static void main(String[] args) {
		FileSetsForm form = new FileSetsForm();
	}

}
