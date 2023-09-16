package tests;

import controller.PersonAlreadyExistsException;
import controller.PersonController;
import controller.PersonNotExistsException;
import model.Employee;
import model.Person;
import org.junit.jupiter.api.Test;
import repository.PersonFileRepository;
import repository.PersonInMemoryRepository;

public class testFile {

    @Test
    void testFindPersonById() throws PersonNotExistsException {

        Person pers1 = new Employee(10, "Coco", "Man", "aaa nr.8", Employee.JobPosition.Developer, 4, 40.0, 6);
        PersonFileRepository persons = new PersonFileRepository();


        PersonController pc = new PersonController(persons);
        try {
            pc.savePerson(pers1);
        } catch (PersonAlreadyExistsException e) {
            e.printStackTrace();
        }
        assert pers1.equals(pc.findPersonById((long) 10));


    }

    @Test
    void testSavePerson() throws PersonNotExistsException {

        Person pers1 = new Employee(12, "AAAAAh", "Man", "aaa nr.8", Employee.JobPosition.Developer, 4, 40.0, 6);
        PersonFileRepository persons = new PersonFileRepository();


        PersonController pc = new PersonController(persons);
        try {
            pc.savePerson(pers1);
        } catch (PersonAlreadyExistsException e) {
            e.printStackTrace();
        }
        assert pers1.equals(pc.findPersonById((long) 12));

    }

    @Test
    void testUpdatePerson() throws PersonNotExistsException {
        Person pers1 = new Employee(13, "AAAAAh", "Man", "aaa nr.8", Employee.JobPosition.Developer, 4, 40.0, 6);
        PersonFileRepository persons = new PersonFileRepository();


        PersonController pc = new PersonController(persons);
        try {
            pc.savePerson(pers1);
        } catch (PersonAlreadyExistsException e) {
            e.printStackTrace();
        }
        assert pers1.equals(pc.findPersonById((long) 13));

        Person pers2 = new Employee(13, "BBBBBBBBB", "Man", "bbb nr.8", Employee.JobPosition.Developer, 4, 40.0, 6);
        pc.updatePerson(pers2);
        assert pers2.equals(pc.findPersonById((long) 13));

    }

    @Test
    void testDeletePerson() throws PersonNotExistsException {

        Person pers1 = new Person(4,"Coco","Man","hgf nr.8");
        PersonInMemoryRepository persons = new PersonInMemoryRepository();

        PersonController pc = new PersonController(persons);
        try {
            pc.savePerson(pers1);
        } catch (PersonAlreadyExistsException e) {
            e.printStackTrace();
        }


        pc.deletePerson(pers1);
        assert persons.findOne((long) 4) == null;
    }

    @Test
    void testCalculateSalary() {

        Employee pers1 = new Employee(1,"Coco","Man","ghg nr.8",Employee.JobPosition.Developer, 4, 40.0, 6  );
        PersonInMemoryRepository persons = new PersonInMemoryRepository();

        PersonController pc = new PersonController(persons);
        try {
            pc.savePerson(pers1);
        } catch (PersonAlreadyExistsException e) {
            e.printStackTrace();
        }


        assert  pc.calculateSalary(pers1, 2) == 480 ;
    }

    @Test
    void testPrintAllSalaries() {

        Employee pers1 = new Employee(1,"Coco","Man","ghg nr.8",Employee.JobPosition.Developer, 4, 40.0, 6  );
        Employee pers2 = new Employee(2, "Iza", "Pop","gfs nr. 3", Employee.JobPosition.Teamleader, 5, 20.0, 8 );
        PersonInMemoryRepository persons = new PersonInMemoryRepository();

        PersonController pc = new PersonController(persons);
        try {
            pc.savePerson(pers1);
            pc.savePerson(pers2);

        } catch (PersonAlreadyExistsException e) {
            e.printStackTrace();
        }


        assert pc.calculateSalary(pers1, 365) == 87600 && pc.calculateSalary(pers2, 365) == 58400;
    }

}