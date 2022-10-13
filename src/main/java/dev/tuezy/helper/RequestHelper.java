package dev.tuezy.helper;

public class RequestHelper {
    public static String getPrefix(String uri){
        String prefix = "";
        if(uri.equalsIgnoreCase("/")){
            prefix = "index";
        }else{
            String[] explodedURI = uri.split("/");
            if(explodedURI.length >= 2 && !explodedURI[1].equalsIgnoreCase("index")){
                prefix = explodedURI[1];
            }
        }
        return prefix;
    }
    public static String compileURL(String uri){
        return uri.replaceAll("(\\{+[^\\/]+})","[a-zA-Z0-9]");
    }
}
