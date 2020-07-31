package com.example.page.js;

import com.google.gson.Gson;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.model.IModel;

public class JsonComponent extends WebComponent {

    private static final long serialVersionUID = -2357713088979921265L;
    private final String HEADER = "\n/*<![CDATA[*/\n";
    private final String FOOTER = "\n/*]]>*/\n";
    private final String varName;
    public JsonComponent(String id, IModel<?> model, String varName) {
        super(id, model);
        this.varName = varName;
    }

    @Override
    public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
        Gson gson = new Gson();
        replaceComponentTagBody(markupStream, openTag,
                HEADER + "var " + varName + " = "
                        + gson.toJson(getDefaultModelObject()) + FOOTER);
    }
}