package archon.appsort.model;

import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Category extends BaseElement {
	
	private static final String TypeKey = "type";
	
	private static final String TypeSeparator = ":";

	private ArrayList<Type> types; 
	
	public Category(Element element) {
		super(element);
		
		types = new ArrayList<Type>();

		Elements _types = element.select(TypeKey);
		for (Element type : _types) {
			types.add(new Type(type));
		}
	}
	
	/**
	 * 
	 * @param strings
	 * @return type or null.
	 */
	public String getSortedType(String... strings) {
		for (Type type : types) {
			if (type.isContainTexts(strings)) {
				return this.getValue() + TypeSeparator + type.getValue();
			}
		}
		return null;
	}
	
	public String getSortedCategory(String... strings) {
		if (isContainTexts(strings)) {
			return this.getValue();
		}
		return null;	
	}


	public ArrayList<Type> getTypes() {
		return types;
	}

	public void setTypes(ArrayList<Type> types) {
		this.types = types;
	}

	@Override
	public String toString() {
		return "Category [" + super.toString() + ", list=" + types + "]";
	}
}
