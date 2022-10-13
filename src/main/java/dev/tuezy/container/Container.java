package dev.tuezy.container;

import java.util.HashMap;

public class Container implements ContainerContract{

    protected HashMap<String, Object> items;

    public Container() {
        this.items = new HashMap<>();
    }

    public HashMap<String, Object> getItems() {
        return items;
    }

    @Override
    public Object get(String $id) {
        return this.items.get($id);
    }

    @Override
    public boolean has(String $id) {
        return this.items.containsKey($id);
    }
}
