package controller;

import model.Employee;
import model.Person;
import repository.CrudRepository;
import repository.PersonInMemoryRepository;

import java.util.List;

public class PersonController {

    private CrudRepository<Person> persons;

    public PersonController(CrudRepository<Person> persons) {
        this.persons = persons;
    }

    public CrudRepository<Person> getPersons() {
        return persons;
    }

    public Person findPersonById(long id) throws PersonNotExistsException {
        Person p = persons.findOne(id);
        if(p==null)
        {
            throw new PersonNotExistsException("Person doesn't exist");
        }
        return persons.findOne(id);
    }

    public Iterable<Person> findAll(){
        return persons.findAll();
    }

    public Person savePerson(Person p)throws PersonAlreadyExistsException{
        Person q = persons.findOne(p.getId());
        if(q!=null)
        {
            throw new PersonAlreadyExistsException("Person exists");
        }
        return persons.save(p);
    }

    public Person updatePerson(Person p)throws PersonNotExistsException {
        Person q = persons.findOne(p.getId());
        if(q==null)
        {
            throw new PersonNotExistsException("Person doesn't exist");
        }
        return  persons.update(p);
    }

    public Person deletePerson(Person p) throws PersonNotExistsException {
        Person q = persons.findOne(p.getId());
        if(q==null)
        {
            throw new PersonNotExistsException("Person doesn't exist");
        }
        return persons.delete(p.getId());
    }

    public double calculateSalary(Employee e, Integer nrOfDays){
        return e.getHourlyPayment() * e.getHoursPerDay() * nrOfDays;
    }

}
