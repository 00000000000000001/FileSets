package gui;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JLabel;

public class StatusBars  {
	private static StatusBars statusBars;
	private String occ = "analysing ...";
	private String done = "";
	private JLabel label;
	private Map<Object, Boolean> processes = new HashMap<>();

	private StatusBars(JLabel canvas) {
		super();
		this.label = canvas;
	}

	public static StatusBars getInstance(JLabel canvas) {
		if (statusBars == null) {
			return statusBars = new StatusBars(canvas);
		} else {
			return statusBars;
		}
	}

	public synchronized void start(Object process) {
		this.processes.put(process, true);
		label.setText(occ);
	}

	public void stop(Object process) {
		this.processes.put(process, false);
		if (!this.processes.values().contains(true)) {
			label.setText(done);
		}
	}

}
