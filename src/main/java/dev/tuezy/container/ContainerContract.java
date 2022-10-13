package dev.tuezy.container;

interface ContainerContract{
    public Object get(String $id);
    public boolean has(String $id);
}
