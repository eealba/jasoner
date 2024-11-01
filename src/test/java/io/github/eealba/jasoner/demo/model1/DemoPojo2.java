package io.github.eealba.jasoner.demo.model1;

public class DemoPojo2 extends DemoPojo{
    public String address2;
    public String profession;
    public static DemoPojo2 joeDoe2() {
        DemoPojo res = new DemoPojo2()
                .setName("John")
                .setLastName("Doe")
                .setAge(30)
                .setAddress("New York");
        DemoPojo2 res2 = (DemoPojo2) res;
        res2.address2 = "New Jersey";
        res2.profession = "Developer";
        return res2;
    }

}
