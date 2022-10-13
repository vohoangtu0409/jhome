package dev.tuezy.container;

public class IoC {
    protected Container resolved;
    protected Container binding;

    private static IoC instance = null;

    private IoC() {
        this.resolved = new Container();
        this.binding = new Container();
    }

    public synchronized static IoC getInstance(){
        if(instance == null)
            instance = new IoC();
        return instance;
    }

    public void bind(String key, String value){
        this.binding.getItems().put(key,value);
    }

    public void instance(String key, Object value){
        this.resolved.getItems().put(key,value);
    }

    public Object get(String key){
        return this.has(key) != null ? this.has(key).get(key) : null;
    }

    public Container has(String key){
        if(this.resolved.has(key)) return  this.resolved;
        if(this.binding.has(key)) return this.binding;
        return null;
    }
}
