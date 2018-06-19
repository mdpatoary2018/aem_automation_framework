package com.cqblueprints.testing.cq.base;

/**
 * Model for the TestEnvironment object used to load settings about a 
 * particular test environment including authentication info 

 */
public class TestEnvironment {
	private String authorUrl;
	private String publishUrl;
	private String testUser;
	private String testPassword;
	private String version;
	private String browser;
	private String proxyEnabled;
	
	/**
	 * @author MdShohid H Patoary
	 */
	 
	public String getAuthorUrl() {
		return authorUrl;
	}
	public void setAuthorUrl(String authorUrl) {
		this.authorUrl = authorUrl;
	}
	
	public String getProxyEnabled() {
		return proxyEnabled;
	}
	public void setProxyEnabled(String proxyEnabled) {
		this.proxyEnabled = proxyEnabled;
	}
	
	public String getPublishUrl() {
		return publishUrl;
	}
	public void setPublishUrl(String publishUrl) {
		this.publishUrl = publishUrl;
	}
	public String getTestUser() {
		return testUser;
	}
	public void setTestUser(String testUser) {
		this.testUser = testUser;
	}
	public String getTestPassword() {
		return testPassword;
	}
	public void setTestPassword(String testPassword) {
		this.testPassword = testPassword;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getBrowser() {
		return browser;
	}
	
	public void setBrowser(String browser) {
		this.browser = browser;
	}
		
}
