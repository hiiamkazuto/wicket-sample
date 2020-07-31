package com.example.page.js;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.form.HiddenField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class AjaxTargetHiddenField extends HiddenField<String> {
    private static final long serialVersionUID = -4517272943890825002L;
    public AjaxTargetHiddenField(String id, Class<? extends Page> page) {
        super(id);
        setModel(new Model<String>((String)urlFor(page, new PageParameters())));
    }
}