package ru.allteran.sellpo.domain;

public class Employee {
    private long phone;
    private String firstName;
    private String lastName;
    private Dealer dealer;

    private EmployeeKPI kpi;

    public Employee() {
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public EmployeeKPI getKpi() {
        return kpi;
    }

    public void setKpi(EmployeeKPI kpi) {
        this.kpi = kpi;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "phone=" + phone +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
