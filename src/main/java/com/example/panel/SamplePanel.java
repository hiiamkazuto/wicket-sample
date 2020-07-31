package com.example.panel;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class SamplePanel extends Panel {

    public SamplePanel(String id){
        super(id);

        IModel<String> panelLabelModel = Model.of("SamplePanelのラベルです");

        add(new Label("panelLabel",panelLabelModel));
    }
}
