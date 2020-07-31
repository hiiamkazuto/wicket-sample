package com.example.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/form")
public class FormPage extends WebPage {

    private static final long serialVersionUID = 6784871137022839632L;

    public FormPage(){
        IModel<String> textFieldModel = Model.of("");

        StatelessForm<Void> form = new StatelessForm<Void>("form"){
            private static final long serialVersionUID = -7646537855520500119L;

            @Override
            protected void onSubmit(){
                // ここにformのボタンを押された時の処理を書く。
                String text = textFieldModel.getObject();
                System.out.println(text);
            }
        };
        add(form);

        TextField<String> textField = new TextField<>("textField",textFieldModel);
        form.add(textField);
    }
}
