package classes;

import java.util.HashMap;
import java.util.Map;

public class MengeModel extends AbstractPublisher{
	private Map<String, String> dict = new HashMap<String, String>();
	private String name = "";

	@Override
	public String toString() {
		StringBuilder items = new StringBuilder();
		// dictionary durchlaufen um item String zusammen zu setzen
		for (Map.Entry<String, String> entry : dict.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    items.append("(" + key + ", " + value + "),");
		}
		// Letztes Komma entfernen, falls items nicht leer ist
		if (items.length() > 0) {
			items.deleteCharAt(items.length() - 1);
		}
		return "{" + items + "}";
	}

	public Map<String, String> getDict() {
		return dict;
	}

	public void setDict(Map<String, String> dictionary) {
		this.dict = dictionary;
		update();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		update();
	}	
	
	
	
}
