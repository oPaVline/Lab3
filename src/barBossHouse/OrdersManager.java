package barBossHouse;

import java.time.LocalDate;
import java.time.LocalDateTime;


public interface OrdersManager {

    int itemsQuantity(String itemName);

    int itemsQuantity(MenuItem item);


    double ordersCostSummary();

    int ordersQuantity();

    int getOrdersCountByDay(LocalDate date);

    Order[] getOrdersByDay(LocalDate date);

    Order[] getClientOrder(Customer customer);
}
