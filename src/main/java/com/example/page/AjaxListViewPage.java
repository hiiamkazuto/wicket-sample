package com.example.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxButton;
import org.apache.wicket.extensions.ajax.markup.html.IndicatingAjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.util.ListModel;
import org.wicketstuff.annotation.mount.MountPath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@MountPath("ajaxlistview")
public class AjaxListViewPage extends WebPage {
    private static final long serialVersionUID = -3154865173282101965L;

    public AjaxListViewPage() {

        var listContainer = new WebMarkupContainer("theContainer");
        listContainer.setOutputMarkupId(true);

        var datas = new ArrayList<MyListItem>();

        IModel<List<MyListItem>> listViewModel = new ListModel<MyListItem>(datas);

        var listView = new ListView<MyListItem>("listView", listViewModel) {
            private static final long serialVersionUID = -8367945106267690398L;

            @Override
            protected void populateItem(final ListItem<MyListItem> item) {
                item.add(new Label("text", item.getModelObject().getText()));
                item.add(new IndicatingAjaxLink<>("deleteButton") {
                    private static final long serialVersionUID = -1980341754487660164L;

                    @Override
                             public void onClick(AjaxRequestTarget target) {
                                 listViewModel.getObject().remove(item.getIndex());
                                 target.add(listContainer);
                             }
                         }
                );
            }
        };

        IModel<String> textFieldModel = Model.of("");
        TextField<String> textField = new TextField<>("textField",textFieldModel);

        var form = new Form<Void>("form");
        form.setOutputMarkupId(true);

        var addButton = new IndicatingAjaxButton("addButton") {
            private static final long serialVersionUID = -9034628018971055450L;

            @Override
            protected void onSubmit(AjaxRequestTarget target) {
                listViewModel.getObject().add(new MyListItem(textFieldModel.getObject()));
                System.out.println(textFieldModel.getObject());
                textFieldModel.setObject("");
                target.add(form);
                target.add(listContainer);
            }
        };

        var link = new Link<>("submitButton") {
            private static final long serialVersionUID = 5396949019790453462L;

            @Override
            public void onClick() {
                datas.stream().forEach(e -> System.out.println(e.getText()));
            }
        };

        form.add(textField);
        form.add(addButton);
        listContainer.add(listView);
        add(listContainer);
        add(form);
        add(link);
    }

    private class MyListItem implements Serializable {
        private static final long serialVersionUID = -1835899937628791240L;
        private String text;

        public MyListItem(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
