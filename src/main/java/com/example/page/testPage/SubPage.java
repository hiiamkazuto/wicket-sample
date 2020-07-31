package com.example.page.testPage;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class SubPage extends WebPage {

    private static final long serialVersionUID = 5844558151557083098L;

    public SubPage() {
        var form = new Form<Void>("form");
        add(form);

        var textFieldModel = Model.of("");
        var textField = new TextField<String>("text", textFieldModel);
        form.add(textField);

        var ajaxButton = new AjaxButton("ajaxButton") {
            private static final long serialVersionUID = 8455750099040478671L;

            @Override
            protected void onSubmit(AjaxRequestTarget target){

            }
        };
        form.add(ajaxButton);
    }

}
