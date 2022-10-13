package dev.tuezy.routing;

public class Action {
    protected Object controller;
    protected String handler;

    public Action(Object controller, String handler) {
        this.controller = controller;
        this.handler = handler;
    }

    public Object getController() {
        return controller;
    }

    public String getHandler() {
        return handler;
    }
}
