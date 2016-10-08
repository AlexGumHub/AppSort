package archon.appsort.sortter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import archon.appsort.model.AppCategory;
import archon.appsort.model.AsProperties;
import archon.appsort.model.Category;

public class AppSortter {
	
	private static final String XmlPath = "in/xml";

	private static final String AppInfosPath = "in/appinfos";

	private static final String OutPath = "out";
	
	private static final String WanDouJia = "WanDouJia";
	
	private static final String YingYongBao = "YingYongBao";

	private static final String Baidu = "Baidu";
	
	private static final String DefaultSortedType = "°²×¿Ó¦ÓÃ";
	
	private static final Logger MyLogger = LogManager.getLogger();
	
	private AppCategory appCategory;

	private AsProperties properties;
	
	private File xmlFile;
	
	private File appinfosFile;

	private File outFile;
	
	private HashSet<String> packageSet;
	
	public void logic() {

		initCommon();
		cleanOutFile();

		initParameters(WanDouJia);
		startSortter();

		initParameters(YingYongBao);
		startSortter();

		initParameters(Baidu);
		startSortter();
	}
	
	private void initCommon() {
		this.properties = new AsProperties();
		this.packageSet = new HashSet<String>();
		this.outFile = new File(OutPath, "sort_AppStore.txt");
		if (!outFile.getParentFile().exists()) {
			outFile.getParentFile().mkdirs();
		}
	}
	
	private void cleanOutFile() {
		if (this.outFile.exists()) {
			this.outFile.delete();
		}
	}

	private void initParameters(String appStore) {

		this.xmlFile = new File(XmlPath, appStore + ".xml");
		if (!xmlFile.exists()) {
			MyLogger.error("Xml file doesn't exist, please check 'in/xml/*.xml'");
			System.exit(-1);
		}

		this.appinfosFile = new File(AppInfosPath, appStore + ".txt");
		if (!appinfosFile.exists()) {
			MyLogger.error("appinfos file doesn't exist, please check 'in/appinfos/*.txt'");
			System.exit(-1);
		}
		
		try {
			appCategory = new AppCategory(xmlFile, properties.getCharset());
		} catch (IOException e) {
			MyLogger.error("AppCategory init error.");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	private void startSortter() {
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(appinfosFile), properties.getCharset()));
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile, true), properties.getCharset()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (br == null || bw == null) {
			MyLogger.error("BufferedReader or BufferedWriter is null.");
			System.exit(-1);
		}
		
		String separator = properties.getSeparator();
		int originalTypeIndex = properties.getOriginalTypeIndex();
		int packageIndex = properties.getPackageIndex();
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				String[] words = line.split(separator);
				if (!packageSet.contains(words[packageIndex])) {
					packageSet.add(words[packageIndex]);
					String sortedType = this.sorts(words[originalTypeIndex]);
					
					bw.write(line + separator + sortedType + "\n");
					bw.flush();
				}
			}
		} catch (IOException e) {
			MyLogger.error("log4jException:", e);
			e.printStackTrace();
		}

		
		try {
			br.close();
			bw.close();
		} catch (IOException e) {
			MyLogger.error("log4jException:", e);
			e.printStackTrace();
		}	
	}
	
	private String sorts(String originalType) {
		String sortedType = null;
		for (Category category : appCategory.getCategories()) {
			sortedType = category.getSortedType(originalType);
			if (sortedType != null) {
				return sortedType;
			}
		}	

		for (Category category : appCategory.getCategories()) {
			sortedType = category.getSortedCategory(originalType);
			if (sortedType != null) {
				return sortedType;
			}
		}
		return DefaultSortedType;
	}
}
