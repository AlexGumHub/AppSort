package archon.appsort.model;

import org.jsoup.nodes.Element;

public class Type extends BaseElement {
	
	public Type(Element element) {
		super(element);
	}
	
	@Override
	public String toString() {
		return "Type [" + super.toString() + "]";
	}
}
