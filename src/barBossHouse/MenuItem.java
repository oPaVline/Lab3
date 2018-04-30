package barBossHouse;

import java.util.Objects;

public abstract  class MenuItem {
    private String name;
    private String description;
    private double cost;
    
private  static final double DEFAULT_COST = 0;

    protected MenuItem(String name, String description) {
        this (name, description,DEFAULT_COST);
    }

    protected MenuItem(String name, String description, double cost ){
        this.name = name;
        this.description = description;
        this.cost = cost;


    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder().append(name)
                .append(",")
                .append(cost)
                .append("p.");
        return str.toString().replaceAll("-1", "");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MenuItem menuItem = (MenuItem) obj;
        return (getName().equals(menuItem.getName()) && getCost() == menuItem.getCost());
    }

    @Override
    public int hashCode() {

        return name.hashCode() ^ Double.hashCode(cost);
    }
}
