package files;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.*;

public class CopyCat implements CopyCatIF {
	private static CopyCat copyCat;
	
	

	private CopyCat() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public static CopyCat getInstance() {
		if (copyCat == null) {
			return copyCat = new CopyCat();
		} else {
			return copyCat;
		}
	}

	@Override
	public boolean cp(String source, String target) {
		// erstelle den Pfad, wenn er noch nicht existiert
		if (exists(source)) {
			// create dir if not exists
			String path = target.replaceAll("/[\\w]+$", "");
			if (!exists(path)) {
				try {
					Files.createDirectories(Paths.get(path));
				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
			}
			// copy file to new dir
			Path s = Paths.get(source);
			Path t = Paths.get(target);
			try {
				return Files.copy(s, t, REPLACE_EXISTING) != null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		return false;

	}

	@Override
	public boolean rm(String path) {
		File file_or_dir = new File(path);

		if (fileOrDir(path) == null) {
			return false;
		}

		// del file if is file
		if (fileOrDir(path) == 'f') {
			return (file_or_dir.delete());
			// del dir if is dir
		} else if (fileOrDir(path) == 'd') {
			File[] allContents = file_or_dir.listFiles();
			if (allContents != null) {
				for (File file : allContents) {
					rm(file.toString());
				}
			}
			return file_or_dir.delete();
		}

		return false;

	}

	@Override
	public boolean exists(String path) {

		if (fileOrDir(path) == null) {
			return false;
		}

		if ((fileOrDir(path) == 'f') || (fileOrDir(path) == 'd')) {
			// if file exists
			return true;
		}

		return false;
	}

	@Override
	public void mkdir(String dir) throws IOException {
		Files.createDirectories(Paths.get(dir));
	}

	@Override
	public Character fileOrDir(String path) {
		Path p = Paths.get(path);

		// is file
		if (!Files.isDirectory(p)) {
			// if file exists
			if (Files.exists(p)) {
				return 'f';
			}
			// is dir
		} else if (Files.isDirectory(p)) {
			return 'd';
		}

		return null;
	}

}
