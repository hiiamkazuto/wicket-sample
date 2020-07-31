package com.example.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxCallListener;
import org.apache.wicket.ajax.form.OnChangeAjaxBehavior;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.stateless.behaviors.StatelessOnChangeAjaxBehavior;

@MountPath("/test")
public class test extends WebPage {

    public test(){
        IModel<String> textAreaModel = Model.of("");
        IModel<String> countLabelModel = Model.of("0");

        TextArea<String> text = new TextArea<>("text", textAreaModel);
        add(text);

        Label countLabel = new Label("count", countLabelModel);
        countLabel.setOutputMarkupId(true);
        countLabel.setOutputMarkupPlaceholderTag(true);

        add(countLabel);

        text.add(new OnChangeAjaxBehavior() {
            @Override
            protected void onUpdate(AjaxRequestTarget target) {
                int  count;
                try{
                    String text = textAreaModel.getObject();
                    text = text.replace("\r\n","");
                    text = text.replace("\r","");
                    text = text.replace("\n","");
                    count = text.length();
                }catch(NullPointerException e){
                    count = 0;
                }
                countLabelModel.setObject(String.valueOf(count));
                target.add(countLabel);
            }
        });

    }
}
