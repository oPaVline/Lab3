package barBossHouse;

import java.util.Objects;

public final class Customer {

    private String firstName;
    private String secondName;
    private int age;
    private Address address;

    private static final String EMPTY_VALUE = "";
    private static final int EMPTY_NUMBER = -1;

    public static final Customer NOT_MATURE_UNKNOWN_CUSTOMER = new Customer(14);
    public static final Customer MATURE_UNKNOWN_CUSTOMER = new Customer(21);

    public Customer() {
        this(EMPTY_VALUE, EMPTY_VALUE, EMPTY_NUMBER, Address.EMPTY_ADDRESS);
    }

    public Customer(int age) {
        this(EMPTY_VALUE, EMPTY_VALUE, age, Address.EMPTY_ADDRESS);
    }

    public Customer(String firstName, String secondName, int age, Address address) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public Address getAddress() {
        return address;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer) obj;
        return getAge() == customer.getAge() &&
                Objects.equals(getFirstName(), customer.getFirstName()) &&
                Objects.equals(getSecondName(), customer.getSecondName()) &&
                Objects.equals(getAddress(), customer.getAddress());
    }

    @Override
    public int hashCode() {

        return firstName.hashCode() ^ secondName.hashCode() ^ Integer.hashCode(age) ^ address.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Customer:").append(secondName)
                .append(" ")
                .append(firstName)
                .append(",")
                .append(age);
        if (address.equals(Address.EMPTY_ADDRESS)) {
            str.append(",")
                    .append(address);
        }
        return str.toString().replaceAll("-1", "");

    }
}
