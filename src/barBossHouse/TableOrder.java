package barBossHouse;

import java.util.Arrays;
import java.util.Objects;
import java.time.LocalDateTime;
import exceptionsPackage.NegativeSizeException;
import exceptionsPackage.UnlawfulActionException;

public class TableOrder implements Order{

    private MenuItem[] items;
    private int size;
    private Customer customer;
    private LocalDateTime dateTime; //todo где инициализация текущим временем?

    private static final int DEFAULT_VALUE = 16;
    public TableOrder() {
        this(DEFAULT_VALUE, Customer.MATURE_UNKNOWN_CUSTOMER);
    }
    //todo Где выброс исключения NegativeSizeException при попытке передать в конструктор отрицательное значение размера массива
    public TableOrder(int TableOrdersNumber, Customer customer) {
        this(new MenuItem[TableOrdersNumber], customer);
    }

    public TableOrder(MenuItem[] items, Customer customer) {
        this.items = items;
        this.customer = customer;
        size = 0;
    }

//todo где выброс UnlawfulActionException?
    public boolean add(MenuItem menuItem) {
        if (size >= items.length) {
            MenuItem[] newMenuItem = new MenuItem[items.length * 2];
            System.arraycopy(items, 0, newMenuItem, 0, items.length);

            items = newMenuItem;
        }

        items[size] = menuItem;
        size++;

        return true;
    }

    public boolean remove(String MenuItemName) {
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (MenuItemName.equals(items[i].getName())) {
                    shiftArray(i);
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    public boolean remove(MenuItem item){
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (item.equals(items[i])) {
                    shiftArray(i);
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    public int removeAll(String MenuItemName) {
        int count = 0;
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (MenuItemName.equals(items[i].getName())) {
                    shiftArray(i);
                    count++;
                    size--;
                }
            }
        }
        return count;

    }

    public int removeAll(MenuItem item){
        int count = 0;
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (item.equals(items[i])) {
                    shiftArray(i);
                    count++;
                    size--;
                }
            }
        }
        return count;

    }

    public int itemsQuantity() {
        return size;
    }

    public int itemsQuantity(String MenuItemName) {
        int count = 0;
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (MenuItemName.equals(items[i].getName())) {
                    count++;
                }
            }
        }
        return count;
    }

    public int itemsQuantity(MenuItem item){
        int count = 0;
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                if (item.equals(items[i])) {
                    count++;
                }
            }
        }
        return count;
    }

    public Dish[] getItems() {
        Dish[] newDishes = new Dish[size];
        System.arraycopy(items, 0, newDishes, 0, size);
        return newDishes;
    }

    public double costTotal() {
        int i;
        double TotalCost = 0;
        for (i = 0; i < items.length; i++)
            TotalCost += items[i].getCost();
        return TotalCost;
    }


    public String[] itemsNames() {
        boolean check = true;
        int uniq = 1;

        //подсчет уникальных названий
        for (int i = 1; i < size; i++) {
            for (int j = i - 1; j != 0; j--) {
                if (items[i].getName().equals(items[j].getName())) {
                    check = false;
                    break;
                }
            }
            if (check)
                uniq++;
            check = true;
        }

        //создание массива уникальных
        String[] itemsNamesArray = new String[uniq];
        int t = 1;
        itemsNamesArray[0] = items[0].getName();

        for (int i = 1; i < size; i++) {
            for (int j = i - 1; j != 0; j--) {
                if (items[i].getName().equals(items[j].getName())) {
                    check = false;
                    break;
                }
            }
            if (check) {
                itemsNamesArray[t] = items[i].getName();
                t++;
            }
            check = true;
        }
        return itemsNamesArray;
    }

    public MenuItem[] sortedItemsByCostDesc() {
        int min = 0;
        MenuItem[] sortedArray = new MenuItem[size];
        double buf = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size; j++)
                if (sortedArray[0].getCost() < sortedArray[j].getCost())
                    min++;
            buf = sortedArray[min].getCost();
            sortedArray[min].setCost(sortedArray[0].getCost());
            sortedArray[0].setCost(buf);
        }
        return sortedArray;
    }

    public int itemsCount(String dishName) {
        if (size != 0) {
            int count = 0;
            for (int i = 0; i < size; i++) {
                if (dishName.equals(items[i].getName()))
                    count++;
            }
            return count;
        }
        return 0;

    }

    public void shiftArray(int i) {
        System.arraycopy(items, i + 1, items, i, items.length - i - 1);
        items[items.length - 1] = null;
    }

    public void shiftArray() {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null)
                shiftArray(i);
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    @Override
    public boolean equals(Object obj) {
        //todo где проверка даты заказа?
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        TableOrder order = (TableOrder) obj;

        if (!customer.equals(order.customer)) return false;

        if (itemsQuantity() != order.itemsQuantity()) return false;

        String[] names = itemsNames();

        for (String n : names) {
            if (itemsQuantity(n) != order.itemsQuantity(n)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        //todo где использование даты заказа?
        int size = itemsQuantity();

        MenuItem[] menuItems = getItems();

        int hash = customer.hashCode() ^ Integer.hashCode(size);

        for (MenuItem item : menuItems)
            hash ^= item.hashCode();

        return hash;
    }

    @Override
    public String toString() {
        MenuItem[] items = getItems();
        StringBuilder str = new StringBuilder("TableOrder: " + size + " \n");

        for (MenuItem item : items) {
            str.append(items.toString())
                    .append("\n");
        }

        return str.toString();

    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
