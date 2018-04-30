package barBossHouse;

import java.util.function.Predicate;
import java.util.Objects;

public class OrderManager {

    private TableOrder[] tableOrders;

    OrderManager(int tableCount) {
        tableOrders = new TableOrder[tableCount];
    }

    public void add(TableOrder tableOrder, int tableNumber) {
        tableOrders[tableNumber] = tableOrder;
    }

    public TableOrder getOrder(int tableNumber) {
            return tableOrders[tableNumber];
    }

    public void addDish(Dish dish, int tableNumber) {
        tableOrders[tableNumber].add(dish);
    }

    public void removeOrder(int tableNumber) {
        tableOrders[tableNumber] = null;
    }

/*
    public int freeTableNumber() {
        for (int i = 0; i < tableOrders.length; i++) {
            if (tableOrders[i] == null) {
                return i;
            }
        }
        return -1;
    }
*/

    private int countFreeTable(Predicate<TableOrder> predicate) {
        int i;
        int count = 0;
        for (i = 0; i < tableOrders.length; i++) {
            if (predicate.test(tableOrders[i])) count++;
        }
        return count;
    }

    private int[] createArrTableNum(Predicate<TableOrder> predicate) {
        int[] arrTableNum = new int[countFreeTable(predicate)];
        int i = 0, j = 0;
        while (i < tableOrders.length) {
            if (predicate.test(tableOrders[i])) {
                arrTableNum[j] = i;
                j++;
            }
            i++;
        }
        return arrTableNum;
    }

    public int freeTableNumber() {
        for (int i = 0; i < tableOrders.length; i++) {
            if (Objects.isNull(tableOrders[i])) return i;
        }
        return -1;
    }

    public int[] freeTableNumbers() {
        return createArrTableNum(Objects::isNull);
    }

    public TableOrder[] getTableOrders() {
        TableOrder[] tableOrders;
        int i = 0, j = 0, count = 0;
        for (TableOrder tableOrder : tableOrders = new TableOrder[countFreeTable(p -> !Objects.isNull(p))]) {
        }
        while (i < this.tableOrders.length) {
            if (this.tableOrders[i] != null) {
                tableOrders[j] = this.tableOrders[i];
                j++;
            }
            i++;
        }
        return tableOrders;
    }

    public double orderCostSummary(){
        double cost = 0;
        for (TableOrder o: tableOrders)
            if (o!=null)
                cost+=o.costTotal();
        return cost;
    }

    public int dishQuantity(String dishName){
        int count=0;
        int i=0;

        while(i< tableOrders.length){

            count+= tableOrders[i].itemsCount(dishName);
            i++;

        }
        return count;
    }

}
