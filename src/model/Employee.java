package model;

import java.util.Objects;

public class Employee extends Person{

    public enum JobPosition
    {
        Developer,Tester,ProjectManager,Teamleader
    }
    private JobPosition  jobPosition;
    private Integer levelOfExperience;
    private double hourlyPayment;
    private Integer hoursPerDay;

    public Employee() {
    }

    public Employee(long id, String firstname, String lastname, String address, JobPosition jobPosition, Integer levelOfExperience, double hourlyPayment, Integer hoursPerDay) {
        super(id, firstname, lastname, address);
        this.jobPosition = jobPosition;
        this.levelOfExperience = levelOfExperience;
        this.hourlyPayment = hourlyPayment;
        this.hoursPerDay = hoursPerDay;
    }

    public JobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
    }

    public Integer getLevelOfExperience() {
        return levelOfExperience;
    }

    public void setLevelOfExperience(Integer levelOfExperience) {
        this.levelOfExperience = levelOfExperience;
    }

    public double getHourlyPayment() {
        return hourlyPayment;
    }

    public void setHourlyPayment(double hourlyPayment) {
        this.hourlyPayment = hourlyPayment;
    }

    public Integer getHoursPerDay() {
        return hoursPerDay;
    }

    public void setHoursPerDay(Integer hoursPerDay) {
        this.hoursPerDay = hoursPerDay;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "\nEmployee{" +
                "id= " + getId() +
                ", firstname= " + getFirstname() +
                ", lastname= " + getLastname() +
                ", address= " + getAddress() +
                ", jobPosition= " + jobPosition +
                ", levelOfExperience= " + levelOfExperience +
                ", hourlyPayment= " + hourlyPayment +
                ", hoursPerDay= " + hoursPerDay +
                '}';
    }


    public String toStringFile() {
        return getId() +
                "," + getFirstname() +
                "," + getLastname() +
                "," + getAddress() +
                "," + jobPosition +
                "," + levelOfExperience +
                "," + hourlyPayment +
                "," + hoursPerDay;

    }


    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        if (!super.equals(o)) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.hourlyPayment, hourlyPayment) == 0 &&
                jobPosition == employee.jobPosition &&
                Objects.equals(levelOfExperience, employee.levelOfExperience) &&
                Objects.equals(hoursPerDay, employee.hoursPerDay) &&
                Objects.equals(getId(), employee.getId()) &&
                Objects.equals(getFirstname(), employee.getFirstname()) &&
                Objects.equals(getLastname(), employee.getLastname()) &&
                Objects.equals(getAddress(), employee.getAddress());
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), jobPosition, levelOfExperience, hourlyPayment, hoursPerDay);
    }

    public static Employee toEmployee(String line){
        String[] s = line.split(",");

        long id = Integer.parseInt(s[0]);
        String firstname = s[1];
        String lastname = s[2];
        String address = s[3];
        JobPosition jp = JobPosition.valueOf(s[4]);
        Integer levelOfExp = Integer.parseInt(s[5]);
        Double hourlyPayment = Double.parseDouble(s[6]);
        Integer hoursPerDay = Integer.parseInt(s[7]);

        return new Employee(id,firstname,lastname,address,jp,levelOfExp,hourlyPayment,hoursPerDay);
    }
}

