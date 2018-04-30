package barBossHouse;

public class InternetOrdersManager implements OrdersManager {

    private QueueNode head;
    private QueueNode tail;
    private int size;

    public InternetOrdersManager() {
        size = 0;
    }

    public InternetOrdersManager(Order[] orders) {
        for (Order o : orders) add(o);
    }

    public boolean add(Order order) {
        QueueNode node = new QueueNode(order);

        if (head == null) {
            head = node;
            tail = node;
            head.prev = null;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }

        size++;
        return true;
    }

    public Order order() {
        return head.value;
    }

    public Order remove() {
        if (size != 0) {
            if (size == 1) {
                Order o = head.value;
                head = head.next;
                head.prev = null;
                size--;
                return o;
            } else {
                Order o = head.value;
                head = head.next;
                tail = null;
                size--;
                return o;
            }
        }
        return null;
    }

    public int ordersQuantity() {
        return size;
    }

    @Override
    public Order[] getOrders() {
        Order[] orders = new Order[size];
        QueueNode node = head;

        for (int i = 0; i < size; i++) {
            orders[i] = node.value;
            node = node.next;
        }

        return orders;
    }

    @Override
    public double ordersCostSummary() {
        double cost = 0;
        QueueNode node = head;

        while (node != null) {
            cost += node.value.costTotal();
            node = node.next;
        }

        return cost;
    }

    @Override
    public int itemsQuantity(MenuItem item) {
        int count = 0;
        QueueNode node = head;

        while (node != null) {
            count += node.value.itemsQuantity(item);
            node = node.next;
        }

        return count;
    }

    @Override
    public int itemsQuantity(String itemName) {
        int count = 0;
        QueueNode node = head;

        while (node != null) {
            count += node.value.itemsQuantity(itemName);
            node = node.next;
        }

        return count;
    }


    private class QueueNode {
        private QueueNode next;
        private QueueNode prev;
        private Order value;

        QueueNode(Order value) {
            this.value = value;
        }
    }
}

