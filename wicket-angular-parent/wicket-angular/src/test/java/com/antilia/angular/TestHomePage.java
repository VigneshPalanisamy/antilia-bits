package com.antilia.angular;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.antilia.angular.example.wicketajax.AngularListViewPage;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(AngularListViewPage.class);

		//assert rendered page class
		tester.assertRenderedPage(AngularListViewPage.class);
	}
}
