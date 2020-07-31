package com.example.page.js;

import com.google.gson.Gson;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.handler.TextRequestHandler;
import org.apache.wicket.request.http.WebResponse;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public abstract class JsonPage extends WebPage {
    private static final String CONTENT_TYPE = "application/json";

    @Override
    protected void configureResponse(WebResponse response) {
        super.configureResponse(response);
        response.setContentType(CONTENT_TYPE);
    }
    @Override
    public final void renderPage() {
    }

    public JsonPage(PageParameters params) {
        Gson gson = new Gson();
        getRequestCycle().scheduleRequestHandlerAfterCurrent(
                new TextRequestHandler(CONTENT_TYPE, "UTF-8", gson.toJson(getJsonObject(params))));
    }
    abstract protected Object getJsonObject(PageParameters params);
}