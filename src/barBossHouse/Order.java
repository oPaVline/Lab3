package barBossHouse;

public interface Order {

    boolean add(MenuItem item);

    String[] itemsNames();

    int itemsQuantity();
    int itemsQuantity(MenuItem itemName);
    int itemsQuantity(String itemName);

    MenuItem[] getItems();

    boolean remove(String itemName);
    boolean remove(MenuItem item);

    int removeAll(String itemName);
    int removeAll(MenuItem item);

    MenuItem[] sortedItemsByCostDesc();

    double costTotal();

    Customer getCustomer();
    void setCustomer(Customer customer);

    @Override
    String toString();

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);
}
