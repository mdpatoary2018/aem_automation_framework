package com.cqblueprints.testing.cq.factory;
/**
 * @author MdShohid H Patoary
 */
public class FactoryProducer {

	public static AbstractPageFactory getPageFactory() {
		return new PageFactory();
	}
}
