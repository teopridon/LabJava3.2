package tests;


import controller.PersonController;
import model.Employee;
import model.Person;
import org.junit.jupiter.api.Test;
import repository.PersonInMemoryRepository;

public class testInMemoryRepository {

    @Test
    void testFindPersonById() {

        Person pers1 = new Employee(1,"Coco","Man","aaa nr.8",Employee.JobPosition.Developer, 4, 40.0, 6  );
        PersonInMemoryRepository persons = new PersonInMemoryRepository();
        persons.save(pers1);

        assert pers1.equals(persons.findOne((long) 1));


    }

    @Test
    void testSavePerson() {

        Person pers1 = new Employee(1,"Coco","Man","hgd nr.8",Employee.JobPosition.Developer, 4, 40.0, 6  );
        PersonInMemoryRepository persons = new PersonInMemoryRepository();
        persons.save(pers1);
        Person pers2 = new Person(2, "dre", "dre","drd");
        persons.save(pers2);

        assert pers2.equals(persons.findOne((long) 2));

    }

    @Test
    void testUpdatePerson() {

        Person pers1 = new Person(1,"Coco","Man","vdg nr.8");
        PersonInMemoryRepository persons = new PersonInMemoryRepository();
        persons.save(pers1);

        Person pers2 = new Person(1, "dre", "dre","drd");

        persons.update(pers2);
        assert pers2.equals(persons.findAll().get(0));

    }

    @Test
    void testDeletePerson() {

        Person pers1 = new Person(1,"Coco","Man","hgf nr.8");
        PersonInMemoryRepository persons = new PersonInMemoryRepository();
        persons.save(pers1);

        persons.delete(pers1.getId());
        assert persons.findOne((long) 1) == null;
    }

    @Test
    void testCalculateSalary() {

        Employee pers1 = new Employee(1,"Coco","Man","ghg nr.8",Employee.JobPosition.Developer, 4, 40.0, 6  );
        PersonInMemoryRepository persons = new PersonInMemoryRepository();
        persons.save(pers1);

        PersonController pc = new PersonController(persons);

        assert  pc.calculateSalary(pers1, 2) == 480 ;
    }

    @Test
    void testPrintAllSalaries() {

        Employee pers1 = new Employee(1,"Coco","Man","ghg nr.8",Employee.JobPosition.Developer, 4, 40.0, 6  );
        Employee pers2 = new Employee(2, "Iza", "Pop","gfs nr. 3", Employee.JobPosition.Teamleader, 5, 20.0, 8 );
        PersonInMemoryRepository persons = new PersonInMemoryRepository();
        persons.save(pers1);
        persons.save(pers2);


        PersonController pc = new PersonController(persons);

        assert pc.calculateSalary(pers1, 365) == 87600 && pc.calculateSalary(pers2, 365) == 58400;
    }


}
