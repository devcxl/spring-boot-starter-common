package cn.devcxl.common.utils;

/**
 * @author Devcxl
 */
public class ThreadLocalUtils {

    public static final ThreadLocal<String> name = new ThreadLocal<>();

    public static String getName(){
        return name.get();
    }

    public static void setName(String name){
        ThreadLocalUtils.name.set(name);
    }

    public static void remove(){
        name.remove();
    }
}
