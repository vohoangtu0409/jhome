package dev.tuezy.routing;

import dev.tuezy.container.IoC;
import dev.tuezy.helper.RequestHelper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Router {
    protected String prefix;
    protected Map routes;

    public Router() {
        this.routes = new HashMap<String, Route>();
    }

    public Router create(Type method, String uri, Action action){
        Route route = new Route(method, uri, action);
        this.routes.put(route.getComplidedURI(), route);
        return this;
    }

    public Router get(String uri, Action action){
        return this.create(Type.GET, uri, action);
    }

    public Router post(String uri, Action action){
        return this.create(Type.POST, uri, action);
    }

    public Router prefix(String prefix){
        this.prefix = prefix;
        return this;
    }

    public Object resolve(HttpServletRequest request){
        Route route = (Route) this.routes.get(this.generateRequestKey(request));
        System.out.println(route);
        return null;
    }

    private String generateRequestKey(HttpServletRequest request){
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();

        uri = uri.replace(contextPath, "");

        String prefix = RequestHelper.getPrefix(uri);
        String method = request.getMethod();

        return method+ "/" + prefix + "/" + RequestHelper.compileURL(uri);
    }
}
