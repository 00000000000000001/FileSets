package mvc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.SwingUtilities;

import files.Hash;
import gui.GUI;

public class SetController {
	private SetModel setModel;
	private final static String PATH_SELF = System.getProperty("user.dir");

	public SetController() {
		super();
		this.setModel = new SetModel();
	}

	public SetModel getSetModel() {
		return setModel;
	}

	public void setSetModel(SetModel setModel) {
		this.setModel = setModel;
	}
	

	public void analyze(String name) throws IOException, NoSuchAlgorithmException {

		
		Path path = Paths.get(name);
		if (!Files.isDirectory(path)) { // add single file
			
			calcHashAndAdd(name);
			
			
		} else if (Files.isDirectory(path)) { // add directory
			
			
			if (Files.exists(path)) {
				List<String> pathnames = filesWalk(name);
				// For each pathname in the pathnames array
				// add to set
				pathnames.forEach(file -> {
					try {
						calcHashAndAdd(file);
					} catch (NoSuchAlgorithmException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				});
			}
			
			
		} else {
			throw new IOException();
		}
		
	}
	
	private List<String> filesWalk(String name) {
		List<String> result;
		try (Stream<Path> walk = Files.walk(Paths.get(name))) {
            // We want to find only regular files
            result = walk.filter(Files::isRegularFile)
            		// We want to find only visible files
            		// TODO add option here
            		.filter(x -> {
				try {
					return !Files.isHidden(x);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			})
                    .map(x -> x.toString()).collect(Collectors.toList());
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
		
	}
	
    private void increaseProgress() {
        SwingUtilities.invokeLater(() -> {
            int n = GUI.getInstance().getProgressBar().getValue();
            GUI.getInstance().getProgressBar().setValue(n + 1);
        });
    }
	
	private void calcHashAndAdd(String filePath) throws NoSuchAlgorithmException, IOException {
		
		
		Map<String, String> dict = setModel.getDict();
		// needs to be checked, if exists
		Path path = Paths.get(filePath);

		if (Files.exists(path)/* && file.canRead()*/) {
			// Check if file already exists
			String hash = Hash.getInstance().getMd5().file(filePath);
			if (!contains(hash)) {
				// convert relative path to absolute path
				if (filePath.charAt(0) == '.') {
					filePath = filePath.replaceFirst("(.)", PATH_SELF);
				}
				// add to dict
				dict.put(hash, filePath);
				// finally set new dict
				setModel.setDict(dict);
			}
		}
		
		// increase progressbar
        increaseProgress();
		
		
	}
	
	public boolean contains(String hash) {
		for (String key : setModel.getDict().keySet()) {
		    if (key.equals(hash.toUpperCase())) {
		    	return true;
		    }
		}
		return false;
	}

	public void add(String key, String value) {
		Map<String, String> dict = this.getSetModel().getDict();
		dict.put(key, value);
		this.getSetModel().setDict(dict);
	}
	
	public String toString() {
		return setModel.toString();
	}
	
}
