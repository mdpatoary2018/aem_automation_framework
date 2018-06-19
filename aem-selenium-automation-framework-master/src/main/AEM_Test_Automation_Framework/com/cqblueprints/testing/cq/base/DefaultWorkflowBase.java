package com.cqblueprints.testing.cq.base;
/**
 * @author MdShohid H Patoary
 */
import org.apache.http.client.ClientProtocolException;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;

import static com.cqblueprints.testing.cq.base.BaseActions.ACTIONS;

public class DefaultWorkflowBase extends TestBase {
	private static final String TEST_PAGE_PARENT = "/content/geometrixx/en/";
	protected static final String TEST_PAGE_NAME = "testpage";
	private static final String TEST_PAGE_TEMPLATE = "/apps/geometrixx/templates/contentpage";
	protected static final String TEST_PAGE = TEST_PAGE_PARENT+TEST_PAGE_NAME+".html"; 		
	protected static final String INBOX_URI = "/inbox";
	
	@Before
	public void startDriver() throws ClientProtocolException, IOException {
		ACTIONS.createPage(TEST_PAGE_NAME, TEST_PAGE_PARENT, TEST_PAGE_TEMPLATE, environment);
	}
		
	@After
	public void closeDriver() throws ClientProtocolException, IOException {
		ACTIONS.deletePage(TEST_PAGE.replace(".html", ""), environment);
		driver.quit();
	}
}
