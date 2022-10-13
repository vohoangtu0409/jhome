package tuezy.cores;

import dev.tuezy.container.IoC;
import dev.tuezy.routing.Router;
import tuezy.Application;

public class Core {
    public static IoC ioc(){
        return IoC.getInstance();
    }

    public static Application app(){
        return (Application) ioc().get("app");
    }

    public static Router router(){
        IoC ioC = ioc();
        if(ioC.has("router") == null){
            Router router = new Router();
            ioC.instance("router", router);
        }
        return (Router) ioC.get("router");
    }
}
