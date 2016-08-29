package archon.appsort.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


public class AsProperties {
	private String charset;
	
	private String separator;
	
	private int originalTypeIndex;
	
	private int packageIndex;

	public AsProperties() {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(new File("config/config.properties")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.charset = p.getProperty("charset").trim();
		this.separator = p.getProperty("separator");
		this.originalTypeIndex = Integer.parseInt(p.getProperty("original_type_index"));
		this.packageIndex = Integer.parseInt(p.getProperty("package_index"));
	}

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public int getOriginalTypeIndex() {
		return originalTypeIndex;
	}

	public void setOriginalTypeIndex(int originalTypeIndex) {
		this.originalTypeIndex = originalTypeIndex;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public int getPackageIndex() {
		return packageIndex;
	}

	public void setPackageIndex(int packageIndex) {
		this.packageIndex = packageIndex;
	}
}
