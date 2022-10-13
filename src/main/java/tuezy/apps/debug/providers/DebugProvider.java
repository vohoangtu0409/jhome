package tuezy.apps.debug.providers;

import dev.tuezy.routing.Action;
import tuezy.Application;
import tuezy.apps.dashboard.controllers.DashboardController;
import tuezy.apps.debug.providers.controllers.DebugController;
import tuezy.cores.AppProvider;
import tuezy.cores.Core;

public class DebugProvider implements AppProvider {

    @Override
    public void register() {
        this.routes();
    }

    private void routes(){
        Core.router().get("/debug", new Action(DebugController.class, "index"));
    }
}
