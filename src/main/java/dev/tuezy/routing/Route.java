package dev.tuezy.routing;

import dev.tuezy.helper.RequestHelper;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class Route {
    protected Type method;
    protected String prefix;
    protected String uri;
    protected Action action;

    protected String complidedURI;

    public Route(Type method, String uri, Action action) {
        this.method = method;
        this.uri = uri;
        this.action = action;
        this.prefix = this.parsePrefix();
        this.complidedURI = this.method + "/" + this.prefix + "/" + this.compile();
    }

    public Type getMethod() {
        return method;
    }

    public void setMethod(Type method) {
        this.method = method;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getComplidedURI() {
        return complidedURI;
    }

    @Override
    public String toString() {
        return "Route{" +
                "method=" + method +
                ", prefix='" + prefix + '\'' +
                ", uri='" + uri + '\'' +
                ", action=" + action +
                '}';
    }

    private String parsePrefix(){
        return RequestHelper.getPrefix(this.uri);
    }

    private String compile(){
        return RequestHelper.compileURL(this.uri);
    }
}
