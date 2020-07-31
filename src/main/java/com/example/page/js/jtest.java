package com.example.page.js;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/json")
public class jtest extends WebPage {
    public jtest(){
        IModel<String> labelModel = Model.of("label");
        JsonComponent label = new JsonComponent("label",labelModel,"aiueo");
        add(label);
    }
}
