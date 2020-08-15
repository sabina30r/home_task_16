package nix.edu.data;

import nix.edu.annotation.CsvField;

public class PersonalInfo {

    @CsvField
    private String name;

    @CsvField
    private int age;

    @CsvField
    private Gender gender;

    @CsvField
    private String occupation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "name: " + this.name + "; " +
                "age: " + this.age + "; " +
                "gender: " + this.gender + "; " +
                "occupation: " + this.occupation;
    }
}
