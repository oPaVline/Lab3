package barBossHouse;

import exceptionsPackage.AlreadyAddedException;
import exceptionsPackage.FreakingAlcoholicException;

import java.time.LocalDate;

import static barBossHouse.Customer.MATURE_UNKNOWN_CUSTOMER;
import static barBossHouse.Customer.NOT_MATURE_UNKNOWN_CUSTOMER;

public class InternetOrdersManager implements OrdersManager {

    private QueueNode head;
    private QueueNode tail;
    private int size;

    public InternetOrdersManager() {
        size = 0;
    }

    public InternetOrdersManager(Order[] orders) throws AlreadyAddedException, FreakingAlcoholicException {
        for (Order o : orders) {
//                add(o);
        }
    }

    //todo где выброс AlreadyAddedException?++
    public boolean add(Order order) throws AlreadyAddedException, FreakingAlcoholicException {
        if (freakingAlcoholicCustomer(order.getCustomer()))
            throw new FreakingAlcoholicException("He's Freaking Alcoholic!");
        QueueNode node = new QueueNode(order);
        QueueNode current = head;

        while (current!=null){
            if( current.value.getCustomer().equals(order.getCustomer()))
                throw new AlreadyAddedException("Already exist!!");
            current=current.next;
        }

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

    public int getOrdersCountByDay(LocalDate date) {
        int count = 0;
        QueueNode current = head;

        while (current != null) {
            if (date.equals(current.value.getDateTime().toLocalDate()))
                count++;

            current = current.next;
        }

        return count;
    }

    public Order[] getOrdersByDay(LocalDate date) {
        Order[] newOrders = new Order[getOrdersCountByDay(date)];
        QueueNode node = head;

        int j = 0;

        while (node != null) {
            if (date.equals(node.value.getDateTime().toLocalDate())) {
                newOrders[j] = node.value;
                j++;
            }
            node = node.next;
        }

        return newOrders;
    }

    public Order[] getClientOrder(Customer customer) {
        int count = 0;
        QueueNode node = head;

        while (node != null) {
            if (customer.equals(node.value.getCustomer()))
                count++;
            node = node.next;
        }

        Order[] newOrders = new Order[count];
        int j = 0;

        while (node != null) {
            if (customer.equals(node.value.getCustomer())) {
                newOrders[j] = node.value ;
                j++;
            }
            node = node.next;
        }

        return newOrders;
    }

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


    public Customer[] getArrayAlcoholicCustomer(){
        QueueNode node = head;

        while (node != null) {
            if (node.value.hasAlcohol()) {
                node.value.getCustomer().orderAlcoholCustomerQuantity();
            }
            node = node.next;
        }

       int sizeArr = 0;
       node = head;

        while (node != null){
        if (node.value.getCustomer().orderAlcoholCustomerQuantity() > 2) sizeArr++;
    }

        int j=0;
        Customer [] customersNew = new Customer[sizeArr];
        node = head;

        while (node != null){
            if (node.value.getCustomer().orderAlcoholCustomerQuantity()>2) {
                customersNew[j] = node.value.getCustomer();
                j++;
            }
        }
        return customersNew;
    }


    public boolean freakingAlcoholicCustomer(Customer customer){
        if (customer.equals(NOT_MATURE_UNKNOWN_CUSTOMER)|| customer.equals(MATURE_UNKNOWN_CUSTOMER ))
            return false;
        for (Customer x : getArrayAlcoholicCustomer() ){
           if (customer.equals(x))
               return true;
        }
        return false;
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

