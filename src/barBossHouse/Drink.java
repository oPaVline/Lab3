package barBossHouse;

import java.util.Objects;

public final class Drink extends MenuItem implements Alcoholable {

    private double alcoholVol;
    private DrinkTypeEnum type;

    private static final double DEFAULT_NUMBER = 0;
    private static final String EMPTY_STRING = "";

    public Drink(String name, DrinkTypeEnum type) {
        this(name, EMPTY_STRING, DEFAULT_NUMBER, DEFAULT_NUMBER, type);
    }

    public Drink(String name, String description, double cost, DrinkTypeEnum type) {
        this(name, description, cost, DEFAULT_NUMBER, type);
    }

    public Drink(String name, String description, double cost, double alcoholVol, DrinkTypeEnum type) {
        super(name, description, cost);

        if (alcoholVol > 100 || alcoholVol < 0)
            throw new IllegalArgumentException("Alcohol volume  can be only between 0 and 100%%");//todo ну сообщение то нужно добавить В Customer и Address есть, а тут нет(((++

        this.type = type;
        this.alcoholVol = alcoholVol;
    }

    public DrinkTypeEnum getType() {
        return type;
    }

    @Override
    public boolean isAlcoholicDrink() {

        return alcoholVol > 0;
    }

    @Override
    public double getAlcoholVol() {
        return alcoholVol*100;
    }

    @Override
    public String toString() {
        return isAlcoholicDrink() ?
                String.format("Drink: %s, %s", type, super.toString()) :
                String.format("Drink %s, %s, Aclohol: %f %s", type, super.toString(), alcoholVol, getDescription());
                //todo alcoholVol - имеет тип double, в спецификатор %d - для целых чисел. Для вещственных можно использовать %f++
    }

    @Override
    public boolean equals(Object obj) {
       /* if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;*/
        //todo первые 3 условия проверяются в веряии equals() суперкласса - не дублируй код, вызывай версию метода суперкласса++
        Drink drink = (Drink) obj;

        return super.equals(obj) && alcoholVol == drink.alcoholVol && type == drink.type;
    }

    @Override
    public int hashCode() {

        return super.hashCode() ^ Double.hashCode(alcoholVol) ^ type.hashCode();
    }
}
