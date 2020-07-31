package com.example.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/bookmarkablepagelink")
public class BookmarkablePageLinkPage extends WebPage {

    private static final long serialVersionUID = -3356745936848035570L;

    public BookmarkablePageLinkPage() {
        //戻りたいページのクラスを指定する。
        BookmarkablePageLink<Void> returnLink = new BookmarkablePageLink<Void>("return", HomePage.class);
        add(returnLink);
    }
}
