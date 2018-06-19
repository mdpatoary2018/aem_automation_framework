package com.cqblueprints.testing.cq.pageobjects;

/**
 * @author MdShohid H Patoary
 * Provides helper methods to perform actions on the login page
 */
public interface LoginPage {
	
	/**
	 * Logs in using the provided credentials
	 * @param user   username
	 * @param pass   password
	 * @return       A new instance of WelcomePage
	 */
	public WelcomePage loginAs(String user, String pass);
}
