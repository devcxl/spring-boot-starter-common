package cn.devcxl.common;

/**
 * @author devcxl
 */
public class SingleDemo {
    public static void main(String[] args) {
        Util instance = Util.getInstance();
        instance.printLog("message OK!");
    }
}

class Util {
    private static Util util = new Util();

    private Util() {
    }

    public static Util getInstance() {
        return util;
    }

    public void printLog(String message) {
        System.out.println(message);
    }
}