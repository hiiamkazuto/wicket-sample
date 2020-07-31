package com.example.page;

import org.apache.wicket.ajax.AjaxSelfUpdatingTimerBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;
import org.wicketstuff.annotation.mount.MountPath;

import java.text.SimpleDateFormat;
import java.util.Date;

//@MountPath("timer")
public class timer extends WebPage {
    private static final long serialVersionUID = -1379874346623832800L;

    public timer(){
        IModel<String> timerModel = new IModel<String>(){
            private static final long serialVersionUID = -1569609713265045874L;

            @Override
            public String getObject(){
                Date date = new Date();
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                return format.format(date);
            }
        };

        Label label = new Label("timer", timerModel);
        label.add(new AjaxSelfUpdatingTimerBehavior(Duration.seconds(1)));

        add(label);
    }
}
