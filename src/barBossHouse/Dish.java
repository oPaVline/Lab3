package barBossHouse;

import java.util.Objects;

public class Dish extends MenuItem {

    public Dish(String name, String description) {
        super(name, description);
    }

    public Dish(String name, String description, double cost) {
        super(name, description, cost);
    }

    @Override
    public String toString() {
        return String.format("Dish: %s - %s", super.toString(), getDescription());
    }

    //todo этот метод абсолютный дубль метода equals() суперкласса. Если это так, тогда можешь не переопределять метод вообще++
   /* @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Dish dish = (Dish) obj;
        return (getName().equals(dish.getName()) && getCost() == dish.getCost());
    }*/

    @Override
    public int hashCode() {

        return super.hashCode() ^ super.getDescription().hashCode();
    }


}
