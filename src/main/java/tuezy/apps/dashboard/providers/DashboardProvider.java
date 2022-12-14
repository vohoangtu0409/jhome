package tuezy.apps.dashboard.providers;

import dev.tuezy.routing.Action;
import tuezy.Application;
import tuezy.apps.dashboard.controllers.DashboardController;
import tuezy.cores.AppProvider;
import tuezy.cores.Core;

public class DashboardProvider implements AppProvider {

    @Override
    public void register() {
        this.routes();
    }

    private void routes(){
        Core.router().get("/dashboard", new Action(DashboardController.class, "index"));
    }
}
