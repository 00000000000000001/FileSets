package classes;

import java.awt.BorderLayout;
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
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class FileSetsForm extends JFrame {
	private static final long serialVersionUID = 1L;
	JButton bOpen = new JButton("open");
	public static JTextArea lMengen = new JTextArea();
	public static JTextArea lMenge = new JTextArea();
	FileSetsControllerIF fileSetsController = new FileSetsController();
	
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
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		// open button
		panel.add(bOpen, BorderLayout.PAGE_START);
		// Mengen View
		panel.add(lMengen, BorderLayout.LINE_START);
		// TODO Menge View
		panel.add(lMenge, BorderLayout.CENTER);
		
		// Create new JFrame with title "FileSets"
		this.setTitle("FileSets");
		
		this.add(panel);
		
		
		// exit on close button
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /* Wir setzen die Breite und die Höhe 
        unseres Fensters auf 200 Pixel */        
		this.setSize(300,200);
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
