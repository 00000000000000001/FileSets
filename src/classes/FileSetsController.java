package classes;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class FileSetsController implements FileSetsControllerIF{
	FileSetsModel fileSetsModel = new FileSetsModel();
	FileSetsView fileSetsView = new FileSetsView();
	
	
	
	public FileSetsController() {
		super();
		// TODO MVC initialisieren
		fileSetsModel.addView(fileSetsView);
		
		fileSetsView.subscribe(fileSetsModel);
	}

	@Override
	public MengeControllerIF create() throws NoSuchAlgorithmException, IOException {
		// neue Menge
		MengeControllerIF menge = new MengeController(this.getFileSetsView());
		// Liste der Mengen holen
		Map<Integer, MengeControllerIF> mengen = fileSetsModel.getMengen();
		// id inkrementieren
		fileSetsModel.setMaxID(fileSetsModel.getMaxID() + 1);
		// Name der neuen Menge anpassen
		menge.setName(name(fileSetsModel.getMaxID()));
		// neue Menge zur Menge der Mengen hinzufügen
		mengen.put(fileSetsModel.getMaxID(), menge);
		fileSetsModel.setMengen(mengen);
		return menge;
	}

	private String name(int id) {
		/*
		 * 0 => A
		 * 1 => B
		 * ...
		 * 25 => Z
		 * 26 => AA
		 * 27 => BB
		 * ...
		 * 51 = ZZZ
		 * 52 => AAA
		 * ...
		 */
		StringBuilder name = new StringBuilder("");
		char c = fileSetsModel.getAlphabet()[( id % 26 )];
		name.append(c);
		for (int i = 0; i < ( id / 26 ); ++i) {
			name.append(c);
		}
		return name.toString();
	}

	@Override
	public MengeControllerIF create(String filename) throws NoSuchAlgorithmException, IOException {
		MengeControllerIF menge = create();
		menge.add(filename);
		return menge;
	}

	@Override
	public MengeControllerIF operation(MengeControllerIF menge_A, MengeControllerIF menge_B, String operation) {
		MengeControllerIF res;
		switch(operation) {
			case FileSetsModel.OPERATION_UNION:
				res = new MengeController(this.getFileSetsView());
				for (Map.Entry<String, String> entry : menge_A.getMengeModel().getDict().entrySet()) {
				    String hash = entry.getKey();
				    String file = entry.getValue();
				    res.add(hash, file);
				}
				for (Map.Entry<String, String> entry : menge_B.getMengeModel().getDict().entrySet()) {
				    String key = entry.getKey();
				    String file = entry.getValue();
				    res.add(key, file);
				}
				return res;
			case FileSetsModel.OPERATION_SUBTRACT:
				res = new MengeController(this.getFileSetsView());
				for (Map.Entry<String, String> entry : menge_A.getMengeModel().getDict().entrySet()) {
				    String key = entry.getKey();
				    String file = entry.getValue();
				    if (!menge_B.contains(key)) {
				    	res.add(key, file);
				    }
				}
				return res;
			case FileSetsModel.OPERATION_INTERSECT:
				res = new MengeController(this.getFileSetsView());
				for (Map.Entry<String, String> entry : menge_A.getMengeModel().getDict().entrySet()) {
				    String hash = entry.getKey();
				    String file = entry.getValue();
				    if (menge_B.contains(hash)) {
				    	res.add(hash, file);
				    }
				}
				return res;
			default:
				System.out.println("unknown operation: " + operation);
		}
		return null;
	}

	public FileSetsModel getFileSetsModel() {
		return fileSetsModel;
	}

	public void setFileSetsModel(FileSetsModel fileSetsModel) {
		this.fileSetsModel = fileSetsModel;
	}

	public FileSetsView getFileSetsView() {
		return fileSetsView;
	}

	public void setFileSetsView(FileSetsView fileSetsView) {
		this.fileSetsView = fileSetsView;
	}

	@Override
	public MengeControllerIF get(String name) {
		// TODO returns set according to given name
		for (MengeControllerIF mengeController : this.fileSetsModel.getMengen().values()) {
		    if (mengeController.getMengeModel().getName().equals(name)) {
		    	return mengeController;
		    }
		}
		return null;
	}

}
