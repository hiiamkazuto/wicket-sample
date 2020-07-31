package com.example.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.wicketstuff.annotation.mount.MountPath;
import org.wicketstuff.stateless.components.StatelessAjaxButton;

//AjaxButtonはFormでしか使えない。
@MountPath("ajaxbutton")
public class AjaxButtonPage extends WebPage {

    private static final long serialVersionUID = 2675800162836244735L;

    public AjaxButtonPage() {
        IModel<String> textFieldModel = Model.of("");
        IModel<String> labelModel = Model.of("ここが変わります");

        StatelessForm<Void> form = new StatelessForm<Void>("form");

        //formのtextをadd
        TextField<String> field = new TextField<String>("inputText", textFieldModel);
        form.add(field);

        //入力した文字列を表示する場所
        Label outputLabel = new Label("outputText", labelModel);
        outputLabel.setOutputMarkupId(true); //下のAjaxButtonでtargetにaddして更新するコンポーネントはsetOutputMarkupId(true)をする。
        form.add(outputLabel);

        //今回のメインであるAjaxButton、ボタンを押すと上のLabelが書き換えられる
        AjaxButton ajaxButton = new AjaxButton("ajaxButton") {
            private static final long serialVersionUID = 448122419953128421L;

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                String inputText = textFieldModel.getObject(); //inputTextに入力された文字列を代入する
                labelModel.setObject(inputText);
                target.add(outputLabel); //更新したコンポーネントをtargetにaddする
            }
        };
        form.add(ajaxButton);
        add(form);
    }
}
