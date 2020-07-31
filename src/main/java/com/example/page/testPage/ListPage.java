package com.example.page.testPage;

import org.apache.wicket.ajax.markup.html.navigation.paging.AjaxPagingNavigator;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.ListDataProvider;
import org.wicketstuff.annotation.mount.MountPath;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@MountPath("listpage")
public class ListPage extends WebPage {


    private static final long serialVersionUID = 7586153231169296935L;

    public ListPage() {
        var dataList = new ArrayList<PersonBean>();
        dataList = createDataList();

        var repeat = new DataView<PersonBean>("repeat", new ListDataProvider<PersonBean>(dataList)) {

            private static final long serialVersionUID = 1008224352196861221L;

            @Override
            protected void populateItem(Item<PersonBean> item) {
                PersonBean person = item.getModelObject();
                item.add(new Label("id", person.getId()));
                item.add(new Label("dep", person.getDep()));
                item.add(new Label("rank", person.getRank()));
                item.add(new Label("sex", person.getSex()));
            }
        };
        repeat.setItemsPerPage(3);
        add(repeat);
        add(new AjaxPagingNavigator("navigator", repeat));
    }

    private ArrayList<PersonBean> createDataList() {
        var list = new ArrayList<PersonBean>();
        list.add(new PersonBean("10003248", "営業", "課長", "男"));
        list.add(new PersonBean("10003278", "開発", "社員", "女"));
        list.add(new PersonBean("10003348", "総務", "社員", "男"));
        list.add(new PersonBean("10004341", "人事", "課長", "女"));
        list.add(new PersonBean("10004987", "企画", "社員", "男"));
        list.add(new PersonBean("10005782", "企画", "社員", "男"));
        list.add(new PersonBean("10008711", "総務", "社員", "女"));
        list.add(new PersonBean("10008712", "営業", "社員", "男"));
        list.add(new PersonBean("10008730", "開発", "社員", "女"));
        list.add(new PersonBean("10008882", "企画", "社員", "男"));
        list.add(new PersonBean("10008942", "人事", "社員", "女"));
        list.add(new PersonBean("10009292", "営業", "社員", "女"));
        list.add(new PersonBean("10009498", "営業", "社員", "女"));
        list.add(new PersonBean("10009571", "開発", "社員", "女"));
        list.add(new PersonBean("10003248", "営業", "課長", "男"));
        list.add(new PersonBean("10003278", "開発", "社員", "女"));
        list.add(new PersonBean("10003348", "総務", "社員", "男"));
        list.add(new PersonBean("10004341", "人事", "課長", "女"));
        list.add(new PersonBean("10004987", "企画", "社員", "男"));
        list.add(new PersonBean("10005782", "企画", "社員", "男"));
        list.add(new PersonBean("10008711", "総務", "社員", "女"));
        list.add(new PersonBean("10008712", "営業", "社員", "男"));
        list.add(new PersonBean("10008730", "開発", "社員", "女"));
        list.add(new PersonBean("10008882", "企画", "社員", "男"));
        list.add(new PersonBean("10008942", "人事", "社員", "女"));
        list.add(new PersonBean("10009292", "営業", "社員", "女"));
        list.add(new PersonBean("10009498", "営業", "社員", "女"));
        list.add(new PersonBean("10009571", "開発", "社員", "女"));
        list.add(new PersonBean("10003248", "営業", "課長", "男"));
        list.add(new PersonBean("10003278", "開発", "社員", "女"));
        list.add(new PersonBean("10003348", "総務", "社員", "男"));
        list.add(new PersonBean("10004341", "人事", "課長", "女"));
        list.add(new PersonBean("10004987", "企画", "社員", "男"));
        list.add(new PersonBean("10005782", "企画", "社員", "男"));
        list.add(new PersonBean("10008711", "総務", "社員", "女"));
        list.add(new PersonBean("10008712", "営業", "社員", "男"));
        list.add(new PersonBean("10008730", "開発", "社員", "女"));
        list.add(new PersonBean("10008882", "企画", "社員", "男"));
        list.add(new PersonBean("10008942", "人事", "社員", "女"));
        list.add(new PersonBean("10009292", "営業", "社員", "女"));
        list.add(new PersonBean("10009498", "営業", "社員", "女"));
        list.add(new PersonBean("10009571", "開発", "社員", "女"));
        list.add(new PersonBean("10003248", "営業", "課長", "男"));
        list.add(new PersonBean("10003278", "開発", "社員", "女"));
        list.add(new PersonBean("10003348", "総務", "社員", "男"));
        list.add(new PersonBean("10004341", "人事", "課長", "女"));
        list.add(new PersonBean("10004987", "企画", "社員", "男"));
        list.add(new PersonBean("10005782", "企画", "社員", "男"));
        list.add(new PersonBean("10008711", "総務", "社員", "女"));
        list.add(new PersonBean("10008712", "営業", "社員", "男"));
        list.add(new PersonBean("10008730", "開発", "社員", "女"));
        list.add(new PersonBean("10008882", "企画", "社員", "男"));
        list.add(new PersonBean("10008942", "人事", "社員", "女"));
        list.add(new PersonBean("10009292", "営業", "社員", "女"));
        list.add(new PersonBean("10009498", "営業", "社員", "女"));
        list.add(new PersonBean("10009571", "開発", "社員", "女"));
        return list;
    }

    private class PersonBean implements Serializable {
        private static final long serialVersionUID = 235283428252414025L;
        private String id;
        private String dep;
        private String rank;
        private String sex;

        public PersonBean(String id, String dep, String rank, String sex) {
            this.id = id;
            this.dep = dep;
            this.rank = rank;
            this.sex = sex;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDep() {
            return dep;
        }

        public void setDep(String dep) {
            this.dep = dep;
        }

        public String getRank() {
            return rank;
        }

        public void setRank(String rank) {
            this.rank = rank;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
