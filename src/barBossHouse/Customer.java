package barBossHouse;

import java.util.Objects;
import java.time.LocalDateTime;
import  java.time.LocalDate ;

public final class Customer {

    private String firstName;
    private String secondName;
    private LocalDate birthDay;
    private Address address;
    private int orderAlcohol;

    private static final String EMPTY_VALUE = "";
    private static final int EMPTY_NUMBER = -1;

    public static final Customer NOT_MATURE_UNKNOWN_CUSTOMER =
            new Customer(LocalDate.of(LocalDate.now().getYear() - 14, 1, 1));
    public static final Customer MATURE_UNKNOWN_CUSTOMER =
            new Customer(LocalDate.of(LocalDate.now().getYear() - 18, 1, 1));

    public Customer() {
        this(EMPTY_VALUE, EMPTY_VALUE, null, Address.EMPTY_ADDRESS);
    }

    public Customer(LocalDate birthDay) {
        this(EMPTY_VALUE, EMPTY_VALUE, birthDay, Address.EMPTY_ADDRESS);
    }

    public Customer(String firstName, String secondName, LocalDate birthDay, Address address)  {
        if(birthDay.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Date of birth cant be in the future");
        this.firstName = firstName;
        this.secondName = secondName;
        this.birthDay = birthDay;
        this.address = address;
    }

    public int getAge() {
        return LocalDate.now().getYear() - birthDay.getYear();
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

    public int orderAlcoholCustomerQuantity()
    {
        return ++orderAlcohol;
    }



    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || this.getClass() == this.getClass()) return false;

        Customer object = (Customer) obj;

        return (firstName.equals(object.firstName) && secondName.equals(object.secondName) && birthDay.equals(object.birthDay) && address.equals(object.address));
    }

    @Override
    public int hashCode() {

        return firstName.hashCode() ^ secondName.hashCode() ^ birthDay.hashCode() ^ address.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Customer:").append(secondName)
                .append(" ")
                .append(firstName)
                .append(",")
                .append(birthDay);
        if (address.equals(Address.EMPTY_ADDRESS)) {
            str.append(",")
                    .append(address);
        }
        return str.toString().replaceAll("-1", "");

    }
}
