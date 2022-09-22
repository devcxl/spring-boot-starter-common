package cn.devcxl.common;

enum DrinkType {
    ORANGE, WATERMELON;
}

enum SweetType {
    LOW, MEDIUM, HIGH;
}

enum FactoryType {
    SWEET, DRINK;
}

/**
 * @author devcxl
 */
interface Drink {
    void make();
}

interface Sweet {
    void add();
}

class Orange implements Drink {
    @Override
    public void make() {
        System.out.println("make Orange Juice");
    }
}

class Watermelon implements Drink {
    @Override
    public void make() {
        System.out.println("make Watermelon Juice");
    }
}

class LowSweet implements Sweet {
    @Override
    public void add() {
        System.out.println("add Low sweet");
    }
}

class MediumSweet implements Sweet {
    @Override
    public void add() {
        System.out.println("add Medium sweet");
    }
}

class HighSweet implements Sweet {
    @Override
    public void add() {
        System.out.println("add High sweet");
    }
}


abstract class AbstractFactory {
    abstract Drink makeDrink(DrinkType drinkType);

    abstract Sweet makeSweetness(SweetType sweetType);
}


class DrinkFactory extends AbstractFactory {
    @Override
    Drink makeDrink(DrinkType drinkType) {
        if (DrinkType.ORANGE.equals(drinkType)) {
            return new Orange();
        } else if (DrinkType.WATERMELON.equals(drinkType)) {
            return new Watermelon();
        }
        return null;
    }

    @Override
    Sweet makeSweetness(SweetType sweetType) {
        return null;
    }

}

class SweetFactory extends AbstractFactory {

    @Override
    Drink makeDrink(DrinkType drinkType) {
        return null;
    }

    @Override
    Sweet makeSweetness(SweetType sweetType) {
        if (SweetType.LOW.equals(sweetType)) {
            return new LowSweet();
        } else if (SweetType.MEDIUM.equals(sweetType)) {
            return new MediumSweet();
        } else if (SweetType.HIGH.equals(sweetType)) {
            return new HighSweet();
        }
        return null;
    }
}


class FactoryProducer {

    static AbstractFactory get(FactoryType factoryType) {

        if (FactoryType.SWEET.equals(factoryType)) {
            return new SweetFactory();
        } else if (FactoryType.DRINK.equals(factoryType)) {
            return new DrinkFactory();
        }
        return null;
    }
}

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        AbstractFactory drinkFactory = FactoryProducer.get(FactoryType.DRINK);
        Drink orange = drinkFactory.makeDrink(DrinkType.ORANGE);
        orange.make();
        Drink watermelon = drinkFactory.makeDrink(DrinkType.WATERMELON);
        watermelon.make();

        AbstractFactory sweetFactory = FactoryProducer.get(FactoryType.SWEET);
        Sweet lowSweet = sweetFactory.makeSweetness(SweetType.LOW);
        lowSweet.add();

        Sweet mediumSweet = sweetFactory.makeSweetness(SweetType.MEDIUM);
        mediumSweet.add();

        Sweet highSweet = sweetFactory.makeSweetness(SweetType.HIGH);
        highSweet.add();
    }
}



