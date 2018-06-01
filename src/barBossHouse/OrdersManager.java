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

    public Customer[] getArrayAlcoholicCustomer();

    public boolean freakingAlcoholicCustomer(Customer customer);
}

//искл FreakingAlcoholicException выбрасывается при попытке добавить заказ содержащий алкоголь если данный кастомер уже больше 3х раз заказывал алкоголь