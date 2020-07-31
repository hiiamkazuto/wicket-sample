package com.example.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/label")
public class LabelPage extends WebPage {

    private static final long serialVersionUID = 3867675390538357745L;

    public LabelPage(){
        String text = "これはテキストです";
        IModel<String> labelModel = Model.of(text);
        Label label = new Label("textLabel",labelModel);
        add(label);
    }
}
