package io.github.eealba.jasoner.demo.model2;


import java.util.Objects;

public class AddressPortable {

    private final String addressLine1;
    private final String addressLine2;
    private final String addressLine3;
    private final String adminArea4;
    private final String adminArea3;
    private final String adminArea2;
    private final String adminArea1;
    private final String postalCode;
    private final CountryCode countryCode;
    private final AddressDetails addressDetails;

    private AddressPortable(Builder builder) {
        addressLine1 = builder.addressLine1;
        addressLine2 = builder.addressLine2;
        addressLine3 = builder.addressLine3;
        adminArea4 = builder.adminArea4;
        adminArea3 = builder.adminArea3;
        adminArea2 = builder.adminArea2;
        adminArea1 = builder.adminArea1;
        postalCode = builder.postalCode;
        addressDetails = builder.addressDetails;
        countryCode = Objects.requireNonNull(builder.countryCode);
    }

    public String addressLine1() {
        return addressLine1;
    }

    public String addressLine2() {
        return addressLine2;
    }

    public String addressLine3() {
        return addressLine3;
    }

    public String adminArea4() {
        return adminArea4;
    }

    public String adminArea3() {
        return adminArea3;
    }

    public String adminArea2() {
        return adminArea2;
    }

    public String adminArea1() {
        return adminArea1;
    }

    public String postalCode() {
        return postalCode;
    }

    public CountryCode countryCode() {
        return countryCode;
    }

    public AddressDetails addressDetails() {
        return addressDetails;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String addressLine1;
        private String addressLine2;
        private String addressLine3;
        private String adminArea4;
        private String adminArea3;
        private String adminArea2;
        private String adminArea1;
        private String postalCode;
        private CountryCode countryCode;
        private AddressDetails addressDetails;

        public Builder addressLine1(String value) {
            addressLine1 = value;
            return this;
        }

        public Builder addressLine2(String value) {
            addressLine2 = value;
            return this;
        }

        public Builder addressLine3(String value) {
            addressLine3 = value;
            return this;
        }

        public Builder adminArea4(String value) {
            adminArea4 = value;
            return this;
        }

        public Builder adminArea3(String value) {
            adminArea3 = value;
            return this;
        }

        public Builder adminArea2(String value) {
            adminArea2 = value;
            return this;
        }

        public Builder adminArea1(String value) {
            adminArea1 = value;
            return this;
        }

        public Builder postalCode(String value) {
            postalCode = value;
            return this;
        }

        public Builder countryCode(CountryCode value) {
            countryCode = value;
            return this;
        }

        public Builder addressDetails(AddressDetails value) {
            addressDetails = value;
            return this;
        }

        public AddressPortable build() {
            return new AddressPortable(this);
        }

    }
public static class AddressDetails {

    private final String streetNumber;
    private final String streetName;
    private final String streetType;
    private final String deliveryService;
    private final String buildingName;
    private final String subBuilding;

    private AddressDetails(Builder builder) {
        streetNumber = builder.streetNumber;
        streetName = builder.streetName;
        streetType = builder.streetType;
        deliveryService = builder.deliveryService;
        buildingName = builder.buildingName;
        subBuilding = builder.subBuilding;

    }

    public String streetNumber() {
        return streetNumber;
    }

    public String streetName() {
        return streetName;
    }

    public String streetType() {
        return streetType;
    }

    public String deliveryService() {
        return deliveryService;
    }

    public String buildingName() {
        return buildingName;
    }

    public String subBuilding() {
        return subBuilding;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String streetNumber;
        private String streetName;
        private String streetType;
        private String deliveryService;
        private String buildingName;
        private String subBuilding;

        public Builder streetNumber(String value) {
            streetNumber = value;
            return this;
        }

        public Builder streetName(String value) {
            streetName = value;
            return this;
        }

        public Builder streetType(String value) {
            streetType = value;
            return this;
        }

        public Builder deliveryService(String value) {
            deliveryService = value;
            return this;
        }

        public Builder buildingName(String value) {
            buildingName = value;
            return this;
        }

        public Builder subBuilding(String value) {
            subBuilding = value;
            return this;
        }

        public AddressDetails build() {
            return new AddressDetails(this);
        }

    }

}


}

