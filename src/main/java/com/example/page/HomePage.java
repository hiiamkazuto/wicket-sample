package com.example.page;

import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/home")
public class HomePage extends WebPage {

	private static final long serialVersionUID = 6310362986862421355L;

	public HomePage() {
		BookmarkablePageLink<Void> labelPageLink = new BookmarkablePageLink<Void>("labelPage",LabelPage.class);
		add(labelPageLink);

		BookmarkablePageLink<Void> bookmarkablePageLinkPageLink = new BookmarkablePageLink<Void>("bookmarkablePageLinkPage",BookmarkablePageLinkPage.class);
		add(bookmarkablePageLinkPageLink);

		BookmarkablePageLink<Void> formPageLink = new BookmarkablePageLink<Void>("formPage",FormPage.class);
		add(formPageLink);

		BookmarkablePageLink<Void> ajaxButtonPageLink = new BookmarkablePageLink<Void>("ajaxButtonPage", AjaxButtonPage.class);
		add(ajaxButtonPageLink);

		BookmarkablePageLink<Void> usePanelPageLink = new BookmarkablePageLink<Void>("usePanelPage",UsePanelPage.class);
		add(usePanelPageLink);
	}
}
