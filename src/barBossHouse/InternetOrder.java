package barBossHouse;

import exceptionsPackage.UnlawfulActionException;

import java.time.LocalTime;
import java.util.Objects;
import java.time.LocalDateTime;

public class InternetOrder implements Order{

    private int size;
    private ListNode head;
    private ListNode tail;
    private Customer customer;
    private LocalDateTime dateTime; //todo где инициализация текущим временем?++

    public InternetOrder() {
        size = 0;
    }

    public InternetOrder(MenuItem[] items, Customer customer) {
        for (MenuItem i : items) this.add(i);
        this.customer = customer;
        dateTime = LocalDateTime.now();
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.dateTime = orderTime;
    }
    public LocalDateTime getOrderTime() {
        return dateTime;
    }

    //todo где выброс UnlawfulActionException?++
    public boolean add(MenuItem item) throws UnlawfulActionException {
        if (item instanceof Drink) {
            if (customer.getAge() < 18 || (LocalTime.now().getHour() > 23) || (LocalTime.now().getHour() < 6)) {
                throw new UnlawfulActionException("You can't buy alcholol now!");
            }
        }

        ListNode node = new ListNode(item);

        if (head == null) {
            head = node;
            tail = node;
        } else
            tail.setNext(node);

        size++;
        return true;

    }

    public void remove(ListNode node, ListNode prev) {
        if (prev != null) {
            prev.setNext(node.getNext());

            if (node.getNext() == null) {
                tail = prev;
            }
        } else {
            head = head.getNext();

            if (head == null) {
                tail = null;
            }
        }
    }

    public boolean remove(String itemName) {
        ListNode node = head;
        ListNode prev = null;

        while (node != null) {
            if (itemName.equals(node.value.getName())) {
                remove(node, prev);

                size--;

                return true;
            }

            prev = node;
            node = node.getNext();
        }


        return false;
    }

    public boolean remove(MenuItem item) {
        ListNode node = head;
        ListNode prev = null;

        while (node != null) {
            if (item.equals(node.value)) {
                remove(node, prev);

                size--;

                return true;
            }

            prev = node;
            node = node.getNext();
        }

        return false;
    }

    public int removeAll(String itemName) {
        ListNode node = head, prev = null;
        int count = 0;
        while (node != null) {
            if (itemName.equals(node.value.getName())) {
                remove(node, prev);
                size--;
                count++;
            }
            prev = node;
            node = node.getNext();
        }

        return count;
    }

    public int removeAll(MenuItem item) {
        ListNode node = head, prev = null;
        int count = 0;
        while (node != null) {
            if (item.equals(node.value)) {
                remove(node, prev);
                size--;
                count++;
            }
            prev = node;
            node = node.getNext();
        }

        return count;
    }

    public int itemsQuantity() {
        return size;
    }

    public int itemsQuantity(String itemName) {
        int i, count = 0;
        ListNode order = head;

        for (i = 0; i < size; i++) {
            if (itemName.equals(order.value.getName())) count++;
            order = order.getNext();
        }

        return count;
    }

    public int itemsQuantity(MenuItem item) {
        int i, count = 0;
        ListNode order = head;

        for (i = 0; i < size; i++) {
            if (item.equals(order.value)) count++;
            order = order.getNext();
        }

        return count;
    }

    public MenuItem[] getItems() {
        int i;
        MenuItem[] items = new MenuItem[size];
        ListNode node = head;

        for (i = 0; i < size; i++) {
            if (node != null)
                items[i] = node.value;
            node = node.getNext();
        }

        return items;
    }


    public String[] itemsNames() {
        boolean check = true;
        int uniq = 1;
        MenuItem[] arr = getItems();

        //подсчет уникальных названий
        for (int i = 1; i < size; i++) {
            for (int j = i - 1; j != 0; j--) {
                if (arr[i].getName().equals(arr[j].getName())) {
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
        itemsNamesArray[0] = arr[0].getName();

        for (int i = 1; i < size; i++) {
            for (int j = i - 1; j != 0; j--) {
                if (arr[i].getName().equals(arr[j].getName())) {
                    check = false;
                    break;
                }
            }
            if (check) {
                itemsNamesArray[t] = arr[i].getName();
                t++;
            }
            check = true;
        }
        return itemsNamesArray;
    }

    public MenuItem[] sortedItemsByCostDesc() {
        MenuItem[] sorteditems = getItems();

        int min = 0;
        double buf = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 1; j < size; j++)
                if (sorteditems[0].getCost() < sorteditems[j].getCost())
                    min++;
            buf = sorteditems[min].getCost();
            sorteditems[min].setCost(sorteditems[0].getCost());
            sorteditems[0].setCost(buf);
        }
        return sorteditems;
    }

    public double costTotal() {
        double cost = 0;
        int i;
        ListNode node = head;

        for (i = 0; i < size; i++) {
            cost += node.value.getCost();
            node = node.getNext();
        }

        return cost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object obj) {
        //todo где проверка даты заказа?++
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        InternetOrder order = (InternetOrder) obj;

        if (!customer.equals(order.customer)) return false;

        if (itemsQuantity() != order.itemsQuantity()) return false;

        if (!this.getOrderTime().equals(order.getOrderTime())) return false;

        String[] names = itemsNames();

        for (String n : names) {
            if (itemsQuantity(n) != order.itemsQuantity(n)) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        //todo где использование даты заказа?++

//        MenuItem[] menuItems = getItems();//todo не надо массив создавать. циклом проходи по нодам и извлекай value++

        int hash = customer.hashCode() ^ Integer.hashCode(size)^head.hashCode()^tail.hashCode()^dateTime.hashCode();

        /*for (MenuItem item : menuItems)
            hash ^= item.hashCode();*/

        ListNode current = head;
        while (current!=null) {
            hash ^= current.value.hashCode();
        }

        return hash;
    }

    @Override
    public String toString() {
//        MenuItem[] items = getItems(); //todo не надо массив создавать. циклом проходи по нодам и извлекай value++
        StringBuilder str = new StringBuilder("InternetOrder:\n" + customer.toString()+size + " \n");
        ListNode current = head;

        while (current!=null) {
            str.append(current.value.toString())
                    .append("\n");
        }

       /* for (MenuItem item : items) {
            str.append(items.toString())
                    .append("\n");
        }*/

        return str.toString();


    }

    public static class ListNode {
        private ListNode next;
        private MenuItem value;

        ListNode(MenuItem menuItem) {
            this.value = menuItem;
        }

        ListNode getNext() {
            return next;
        }

        void setNext(ListNode next) {
            this.next = next;
        }
    }

    @Override
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean hasAlcohol(){
        ListNode node = head;
        while (node!=null){
            if ((node.value instanceof Drink) && ((Drink) node.value).isAlcoholicDrink())
                return true;
            node = node.next;
        }
        return false;
    }

}

