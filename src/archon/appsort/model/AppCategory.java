package archon.appsort.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AppCategory {

	private static final String CategoryKey = "category";

	private ArrayList<Category> categories;

	public AppCategory(String xmlFileString) throws IOException {
		this(xmlFileString, "UTF-8");
	}
	
	public AppCategory(String xmlFileString, String charsetName) throws IOException {
		this(new File(xmlFileString), charsetName);
	}
	
	public AppCategory(File xmlFile) throws IOException {
		this(xmlFile, "UTF-8");
	}
	
	public AppCategory(File xmlFile, String charsetName) throws IOException {
		 this(Jsoup.parse(xmlFile, charsetName));	
	}
	
	public AppCategory(Document document) {
		categories = new ArrayList<Category>();
		Elements _categories = document.select(CategoryKey);	
		for (Element category : _categories) {
			categories.add(new Category(category));
		}
	}

	public ArrayList<Category> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<Category> categories) {
		this.categories = categories;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Category category : categories) {
			sb.append(category.toString()).append("\n");
		}
		return sb.toString().trim();
	}
}
