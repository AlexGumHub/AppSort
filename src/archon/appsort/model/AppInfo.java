package archon.appsort.model;

public class AppInfo {
	
	private static final String SEPATATOR = "\t";

	private String appId;
	
	private String appName;

	private String appOriginalType;
	
	private String appDesc;
	
	private String appVersion;
	
	private String appUpdate;

	private String appSize;

	private String appAuthor;

	private String appPackage;
	
	private String appType;
	

	public AppInfo() {}
	
	public AppInfo(String infos) {
		String[] words = infos.split(SEPATATOR);
		this.appName = words[0];
		this.appOriginalType = words[1];
		this.appDesc = words[2];
		this.appVersion = words[3];
		this.appUpdate = words[4];
		this.appSize = words[5];
		this.appAuthor = words[6];
		this.appPackage = words[7];
		this.appType = "";
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppType() {
		return appOriginalType;
	}

	public void setAppType(String appType) {
		this.appOriginalType = appType;
	}

	public String getAppDesc() {
		return appDesc;
	}

	public void setAppDesc(String appDesc) {
		this.appDesc = appDesc;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public String getAppUpdate() {
		return appUpdate;
	}

	public void setAppUpdate(String appUpdate) {
		this.appUpdate = appUpdate;
	}

	public String getAppSize() {
		return appSize;
	}

	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}

	public String getAppAuthor() {
		return appAuthor;
	}

	public void setAppAuthor(String appAuthor) {
		this.appAuthor = appAuthor;
	}

	public String getAppPackage() {
		return appPackage;
	}

	public void setAppPackage(String appPackage) {
		this.appPackage = appPackage;
	}
	
	public String getAppOriginalType() {
		return appOriginalType;
	}

	public void setAppOriginalType(String appOriginalType) {
		this.appOriginalType = appOriginalType;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(appName).append(SEPATATOR)
		  .append(appOriginalType).append(SEPATATOR)
		  .append(appDesc).append(SEPATATOR)
		  .append(appVersion).append(SEPATATOR)
		  .append(appUpdate).append(SEPATATOR)
		  .append(appSize).append(SEPATATOR)
		  .append(appAuthor).append(SEPATATOR)
		  .append(appPackage).append(SEPATATOR)
		  .append(appType);
		return sb.toString().trim();
	}
}
