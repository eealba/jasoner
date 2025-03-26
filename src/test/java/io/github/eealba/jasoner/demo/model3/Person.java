package io.github.eealba.jasoner.demo.model3;

import lombok.Data;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class Person {
    Long personId;
    UUID personUUID;
    BasicData basicData;
    List<Documents> documents;
    List<Address> addresses;
    List<Phone> phones;
    List<Email> emails;
    enum Gender {
        MALE,
        FEMALE,
        OTHER
    }
    enum PersonType {
        NATURAL,
        LEGAL
    }
    enum DocumentType {
       PASSPORT,
       IDENTITY_CARD,
       SOCIAL_SECURITY,
       TAX_ID,
       DRIVING_LICENSE,
       OTHER
    }

    enum TypeUse {
        PERSONAL,
        WORK,
        OTHER
    }

    @Data
    public static class BasicData {
        String firstName;
        String lastName;
        LocalDate birthday;
        Gender gender;
        PersonType personType;
        CountryCode countryOfBirth;
        String cityOfBirth;
        URI photo;

    }

    @Data
    public static class Documents {
        Long personDocumentId;
        CountryCode country;
        DocumentType documentType;
        String documentNumber;
        LocalDate expirationDate;
        LocalDate issueDate;
    }
    @Data
    public static class Address {
        Long personAddressId;
        CountryCode country;
        Long territoryOrganizationId;
        String territorial1;
        String territorial2;
        String territorial3;
        String territorial4;
        String territorial5;
        String city;
        String streetName;
        String streetPremise;
        String floor;
        String door;
        String zipCode;
        String custom;
        TypeUse typeUse;
    }
    @Data
    public static class Phone {
        Long personPhoneId;
        CountryCode country;
        Long phoneNumber;
        Long phoneExtension;
        TypeUse typeUse;
    }

    @Data
    public static class Email {
        Long personEmailId;
        String email;
        TypeUse typeUse;
    }
}
