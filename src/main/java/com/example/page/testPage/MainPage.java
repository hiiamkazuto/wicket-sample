package com.example.page.testPage;

import com.example.page.HomePage;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/testPage")
public class MainPage extends WebPage {

    private static final long serialVersionUID = 6872671679792660649L;
    private ModalWindow modalWindow;

    public MainPage() {
        var model = Model.of("");
        Label label = new Label("label", model);

        modalWindow = new ModalWindow("modal");
        modalWindow.setCssClassName(ModalWindow.CSS_CLASS_GRAY);
        add(modalWindow);

        modalWindow.setPageCreator(new ModalWindow.PageCreator(){
            private static final long serialVersionUID = -6166271337050126267L;

            @Override
            public Page createPage() {
                return new SubPage();
            }
        });

        modalWindow.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
            private static final long serialVersionUID = 4609084031868261485L;

            @Override
            public void onClose(AjaxRequestTarget target) {
                setResponsePage(HomePage.class);

            }
        });

        Form<WebPage> form = new Form<WebPage>("form");
        add(form);

        form.add(label);

        AjaxButton ajaxButton = new AjaxButton("ajaxButton") {
            @Override
            protected void onSubmit(AjaxRequestTarget target){
                System.out.println("submit");
                modalWindow.show(target);
            }
        };
        form.add(ajaxButton);

    }
}
