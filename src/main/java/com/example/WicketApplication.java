package com.example;

import com.example.page.AjaxButtonPage;
import com.example.page.AjaxListViewPage;
import com.example.page.HomePage;
import com.example.page.test;
import org.apache.wicket.Application;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.util.lang.Args;
import org.wicketstuff.annotation.scan.AnnotatedMountScanner;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see com.example.Start#main(String[])
 */
public class WicketApplication extends WebApplication
{
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return HomePage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();

//		super.mount(new NoVersionMapper("timer",timer.class));
		super.getRootRequestMapperAsCompound().add(new NoVersionMapper("ajaxlistviewpage", AjaxListViewPage.class));
		super.getRootRequestMapperAsCompound().add(new NoVersionMapper("ajaxbuttonpage", AjaxButtonPage.class));
		new AnnotatedMountScanner().scanPackage("com.example.page").mount(this);
	}

}
