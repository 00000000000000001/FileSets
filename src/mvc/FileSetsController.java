package mvc;

public class FileSetsController {
	private static FileSetsController fileSetsController;
	private static final String OPERATION_UNION = "union";
	private static final String OPERATION_SUBTRACT = "subtract";
	private static final String OPERATION_INTERSECT = "intersect";

	private FileSetsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static FileSetsController getInstance() {
		if (fileSetsController == null) {
			return fileSetsController = new FileSetsController();
		} else {
			return fileSetsController;
		}
	}

	public SetController operation(SetController menge_A, SetController menge_B, String operation) {
		SetController res;
		switch (operation) {
		case OPERATION_UNION:
			res = new SetController();
			menge_A.getSetModel().getDict().entrySet().stream().forEach(x -> res.add(x.getKey(), x.getValue()));
			menge_B.getSetModel().getDict().entrySet().stream().forEach(x -> res.add(x.getKey(), x.getValue()));
			return res;
		case OPERATION_SUBTRACT:
			res = new SetController();
			menge_A.getSetModel().getDict().entrySet().stream().filter(x -> !menge_B.contains(x.getKey()))
					.forEach(x -> res.add(x.getKey(), x.getValue()));
			return res;
		case OPERATION_INTERSECT:
			res = new SetController();
			menge_A.getSetModel().getDict().entrySet().stream().filter(x -> menge_B.contains(x.getKey()))
					.forEach(x -> res.add(x.getKey(), x.getValue()));
			return res;
		default:
			System.out.println("unknown operation: " + operation);
		}
		return null;
	}

	public SetController get(String name) {
		// returns set by given name
		for (SetController mengeController : FileSetsModel.getInstance().getList()) {
			if (mengeController.getSetModel().getName().equals(name)) {
				return mengeController;
			}
		}
		return null;
	}

}
