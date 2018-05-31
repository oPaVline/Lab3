package barBossHouse;

import java.util.function.Predicate;
import java.util.Objects;
import java.time.LocalDate;
import java.time.LocalDateTime;
import exceptionsPackage.NegativeSizeException;
import exceptionsPackage.UnlawfulActionException;

public class TableOrdersManager implements OrdersManager {
    private Order[] orders;

    //todo Где выброс исключения NegativeSizeException при попытке передать в конструктор отрицательное значение размера массива
    TableOrdersManager(int tableCount) {
        orders = new Order[tableCount];
    }

    //todo где выброс AlreadyAddedException?
    public void add(Order order, int tableNumber) {
        orders[tableNumber] = order;
    }

    public void addItem(MenuItem item, int tableNumber) {
        orders[tableNumber].add(item);
    }

    public Order getOrders(int tableNumber) {
        return orders[tableNumber];
    }

    public void remove(int tableNumber) {
        orders[tableNumber] = null;
    }

    public int remove(Order order) {
        for (int i = 0; i < orders.length; i++) {
            if (order.equals(orders[i])) {
                orders[i] = null;
                return i;
            }
        }

        return -1;
    }

    public int removeAll(Order order) {
        int count = 0;
        for (int i = 0; i < orders.length; i++) {
            if (order.equals(orders[i])) {
                remove(i);
                count++;
            }
        }
        if (count > 0) return count;
        return -1;
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

    private int countFreeTable(Predicate<Order> predicate) {
        int i;
        int count = 0;
        for (i = 0; i < orders.length; i++) {
            if (predicate.test(orders[i])) count++;
        }
        return count;
    }

    private int[] createArrTableNum(Predicate<Order> predicate) {
        int[] arrTableNum = new int[countFreeTable(predicate)];
        int i = 0, j = 0;
        while (i < orders.length) {
            if (predicate.test(orders[i])) {
                arrTableNum[j] = i;
                j++;
            }
            i++;
        }
        return arrTableNum;
    }
//todo где выброс NoFreeTableException?
    public int freeTableNumber() {
        for (int i = 0; i < orders.length; i++) {
            if (Objects.isNull(orders[i])) return i;
        }
        return -1;
    }

    public int[] freeTableNumbers() {
        return createArrTableNum(Objects::isNull);
    }

    public Order[] getTableOrders() {
        Order[] orders;
        int i = 0, j = 0, count = 0;
        for (Order order : orders = new Order[countFreeTable(p -> !Objects.isNull(p))]) {
        }
        while (i < this.orders.length) {
            if (this.orders[i] != null) {
                orders[j] = this.orders[i];
                j++;
            }
            i++;
        }
        return orders;
    }

    public double ordersCostSummary() {
        double cost = 0;
        for (Order o : orders)
            if (o != null)
                cost += o.costTotal();
        return cost;
    }

    public int itemsQuantity(String itemName) {
        int count = 0;
        int i = 0;

        while (i < orders.length) {
            count += orders[i].itemsQuantity(itemName);
            i++;
        }
        return count;
    }

    public int itemsQuantity(MenuItem item) {
        int count = 0;
        int i = 0;

        while (i < orders.length) {
            count += orders[i].itemsQuantity(item);
            i++;
        }

        return count;
    }

    public int ordersQuantity() {
        return orders.length;
    }

    @Override
    public int getOrdersCountByDay(LocalDate date) {
        int count = 0;
        for (Order order : orders) {
            if (date.equals(order.getDateTime().toLocalDate()))
                count++;
        }

        return count;
    }

    @Override
    public Order[] getOrdersByDay(LocalDate date) {
        Order[] newOrders = new Order[getOrdersCountByDay(date)];
        int j = 0;
        for (Order order : orders) {
            if (date.equals(order.getDateTime().toLocalDate())) {
                newOrders[j] = order;
                j++;
            }
        }

        return newOrders;
    }

    @Override
    public Order[] getClientOrder(Customer customer) {
        int count = 0;
        for (Order order : orders) {
            if (customer.equals(order.getCustomer()))
                count++;
        }

        Order[] newOrders = new Order[count];

        int j = 0;
        for (Order order : orders) {
            if (customer.equals(order.getCustomer())) {
                newOrders[j] = order;
                j++;
            }
        }

        return newOrders;
    }

}
