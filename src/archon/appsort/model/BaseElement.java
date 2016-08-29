package archon.appsort.model;

import java.util.ArrayList;

import org.jsoup.nodes.Element;

public abstract class BaseElement {
	
	public static final String SEPARATOR = ",";
	
	private String value;
	private String id;
	private ArrayList<String> texts;
	
	public BaseElement(Element element) {
		this.value = element.attr("name");
		this.id = element.attr("id");
		initTexts(element);
	}
	
	private void initTexts(Element element) {
		String elementText = element.attr("value");
		if (elementText.isEmpty()) return;
		texts = new ArrayList<String>();
		String[] words = elementText.split(SEPARATOR);
		for (String text : words) {
			texts.add(text);
		}
	}
	
	public boolean isContainTexts(String... strings) {
		if (texts == null) {
			return false;
		}

		for (String string : strings) {
			for (String text : texts) {
				if (string.contains(text)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public ArrayList<String> getTexts() {
		return texts;
	}

	public void setTexts(ArrayList<String> texts) {
		this.texts = texts;
	}

	@Override
	public String toString() {
		return "value=" + value + ", id=" + id;
	}
}
