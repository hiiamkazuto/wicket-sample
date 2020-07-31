package com.example.page;

import com.example.panel.SamplePanel;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("/userpanelpage")
public class UsePanelPage extends WebPage {

    private static final long serialVersionUID = 5263442782938038181L;

    public UsePanelPage(){

        //SamplePanelをページにaddする。
        add(new SamplePanel("samplePanel"));

    }
}
