package classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import files.Hash;
import files.HashIF;
import files.MD5;

public class SetController implements SetControllerIF{
	private SetModel mengeModel = new SetModel();
	private SetView mengeView = new SetView();
	private HashIF hash = new Hash();
	public final static String PATH_SELF = System.getProperty("user.dir");
	
	public SetController() {
		super();
		// MVC Initialisierung
		mengeModel.addView(mengeView);
		
		mengeView.subscribe(mengeModel);
	}
	
	public SetController(String filename) throws NoSuchAlgorithmException, IOException {
		this();
		add(filename);
	}
	
	public SetController(FileSetsView fileSetsView) {
		this();
		// MVC Initialisierung
		mengeModel.addView(fileSetsView);

	}

	public SetController(String filename, FileSetsView fileSetsView) throws NoSuchAlgorithmException, IOException {
		this(fileSetsView);
		add(filename);
	}

	@Override
	public String toString() {
		return mengeModel.toString();
	}

	private void addFile(String name) throws NoSuchAlgorithmException, IOException {
		Map<String, String> dict = mengeModel.getDict();
		// needs to be checked, if exists
		Path path = Paths.get(name);
		if (Files.exists(path)) {
			// Check if file already exists
			String hash = this.hash.getSha256().file(name);
			if (!contains(hash)) {
				// convert relative path to absolute path
				if (name.charAt(0) == '.') {
					name = name.replaceFirst("(.)", PATH_SELF);
				}
				// add to dict
				dict.put(hash, name);
				// finally set new dict
				mengeModel.setDict(dict);
			}
		}
	}

	@Override
	public boolean contains(String hash) {
		for (String key : mengeModel.getDict().keySet()) {
		    if (key.equals(hash.toUpperCase())) {
		    	return true;
		    }
		}
		return false;
	}

	private void addDir(String name) throws NoSuchAlgorithmException, IOException {
		// check if dir exists
		Path path = Paths.get(name);
		if (Files.exists(path)) {
			List<String> pathnames = filesWalk(name);
			// For each pathname in the pathnames array
			// add to set
			pathnames.forEach(file -> {
				try {
					addFile(file);
				} catch (NoSuchAlgorithmException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
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

	@Override
	public void add(String name) throws NoSuchAlgorithmException, IOException {
		Path path = Paths.get(name);
		if (!Files.isDirectory(path)) {
			addFile(name);
		} else if (Files.isDirectory(path)) {
			addDir(name);
		} else {
			throw new IOException();
		}
	}
	
	@Override
	public void add(String key, String value) {
		Map<String, String> dict = mengeModel.getDict();
		dict.put(key, value);
		mengeModel.setDict(dict);
	}

	@Override
	public String remove(String hash) {
		Map<String, String> dict = mengeModel.getDict();
		String ret = dict.remove(hash);
		mengeModel.setDict(dict);
		return ret;
	}

	public SetModel getMengeModel() {
		return mengeModel;
	}

	@Override
	public void setName(String name) {
		getMengeModel().setName(name);
	}

	

}
