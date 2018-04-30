package barBossHouse;

import java.util.Objects;

public final class Address {
    private String cityName;
    private int zipCode;
    private String streetName;
    private int buildingNumber;
    private char buildingLetter;
    private int apartmentNumber;

    private static final String DEFAULT_CITY = "Samara";
    private static final String DEFAULT_STRING = "";
    private static final char DEFAULT_WORD = ' ';
    private static final int DEFAULT_NUMBER = -1;

    Address() {
        this(DEFAULT_STRING, DEFAULT_NUMBER, DEFAULT_STRING, DEFAULT_NUMBER, DEFAULT_WORD, DEFAULT_NUMBER);
    }

    Address(String streetName, int buildingNumber, char buildingLetter, int apartmentNumber) {
        this(DEFAULT_CITY, DEFAULT_NUMBER, streetName, buildingNumber, buildingLetter, apartmentNumber);
    }

    public Address(String cityName, int zipCode, String streetName, int buildingNumber, char buildingLetter, int apartmentNumber) {
        this.cityName = cityName;
        this.zipCode = zipCode;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.buildingLetter = buildingLetter;
        this.apartmentNumber = apartmentNumber;
    }

    public static final Address EMPTY_ADDRESS = new Address();

    public String getCityName() {
        return cityName;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public char getBuildingLetter() {
        return buildingLetter;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Your address: ").append(cityName)
                .append(" ")
                .append(zipCode)
                .append(",")
                .append(" ")
                .append(streetName)
                .append(" ")
                .append(buildingNumber)
                .append(buildingLetter)
                .append("-")
                .append(apartmentNumber);
        return str.toString().replaceAll("-1", "");
    }


    @Override
    public int hashCode() {

        return cityName.hashCode() ^ streetName.hashCode() ^ Integer.hashCode(buildingNumber)
                ^ Character.hashCode(buildingLetter) ^ Integer.hashCode(apartmentNumber);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Address address = (Address) obj;
        return getZipCode() == address.getZipCode() &&
                getBuildingNumber() == address.getBuildingNumber() &&
                getBuildingLetter() == address.getBuildingLetter() &&
                getApartmentNumber() == address.getApartmentNumber() &&
                Objects.equals(getCityName(), address.getCityName()) &&
                Objects.equals(getStreetName(), address.getStreetName());
    }
}

