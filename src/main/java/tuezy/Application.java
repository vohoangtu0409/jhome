package tuezy;

import dev.tuezy.container.IoC;
import dev.tuezy.routing.Action;
import dev.tuezy.routing.Route;
import dev.tuezy.routing.Router;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tuezy.apps.dashboard.providers.DashboardProvider;
import tuezy.cores.AppProvider;
import tuezy.cores.controllers.Controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Iterator;

public class Application{
    IoC container;

    ArrayList providers;

    public Application() {
        this.container = IoC.getInstance();
        this.providers = new ArrayList<AppProvider>();

        Router router = new Router();

        // init main app when initialize
        this.container.instance("router", router);
        this.container.instance("app", this);

        //register apps when servlet initialize
        this.registerApp();
        this.resolveProviders();
    }

    private void registerApp(){
        this.providers.add(new DashboardProvider());
    }

    private void resolveProviders(){
        Iterator it = this.providers.iterator();
        while (it.hasNext()){
            AppProvider provider = (AppProvider) it.next();
            provider.register();
        }
    }

    public void handle(HttpServletRequest request, HttpServletResponse response){

        Router router = (Router) IoC.getInstance().get("router");
        Route route = router.resolve(request);
        if(null != route){
            Action action = route.getAction();
            String handler = action.getHandler();

            try {
                Class controllerClass = (Class) action.getController();

                Constructor constructor = controllerClass.getDeclaredConstructor(HttpServletRequest.class, HttpServletResponse.class);
                Controller controller = (Controller) constructor.newInstance(request, response);

//                Controller controller = (Controller) controllerClass.getDeclaredConstructor().newInstance();

                Method method = controllerClass.getMethod(handler);
                Parameter [] parameters = method.getParameters();
                if(parameters.length == 0){
                    method.invoke(controller);
                }else {
                    try {
                        throw new ServletException("Not support controller method with parameters yet");
                    } catch (ServletException e) {
                        throw new RuntimeException(e);
                    }
                }


            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
