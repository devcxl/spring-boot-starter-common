package cn.devcxl.common;

/**
 * @author devcxl
 */
public class FactoryDemo {


    public static void main(String[] args) {
        new FactoryDemo().run();
    }

    public void run() {
        DrinkFactory drinkFactory = new DrinkFactory();
        Drink orangeade = drinkFactory.makeDrink(DrinkType.ORANGE);
        orangeade.make();
        Drink watermelon = drinkFactory.makeDrink(DrinkType.WATERMELON);
        watermelon.make();
    }

    public enum DrinkType {
        ORANGE,
        WATERMELON;

    }

    public interface Drink {
        void make();
    }

    public class DrinkFactory {
        public Drink makeDrink(DrinkType drinkType) {
            if (DrinkType.ORANGE.equals(drinkType)) {
                return new Orange();
            } else if (DrinkType.WATERMELON.equals(drinkType)) {
                return new Watermelon();
            }
            return null;
        }
    }

    public class Orange implements Drink {
        @Override
        public void make() {
            // todo make Orange
            System.out.println("make Orange");
        }
    }

    public class Watermelon implements Drink {
        @Override
        public void make() {
            // todo make Watermelon
            System.out.println("make Watermelon Juice");
        }
    }
}
