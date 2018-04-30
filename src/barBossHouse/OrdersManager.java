package barBossHouse;

public interface OrdersManager  {

    int itemsQuantity(String itemName);

    int itemsQuantity(MenuItem item);

    Order[] getOrders();

    double ordersCostSummary();

    int ordersQuantity();
}
