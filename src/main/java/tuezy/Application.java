package tuezy;

import dev.tuezy.container.IoC;
import dev.tuezy.routing.Router;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tuezy.apps.dashboard.providers.DashboardProvider;
import tuezy.cores.AppProvider;

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
        this.providers.add(new DashboardProvider(this));
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
        router.resolve(request);
    }
}
