package repository;



import model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonInMemoryRepository implements CrudRepository<Person> {


    private List<Person> persons;

    public PersonInMemoryRepository() {
        persons = new ArrayList<Person>();
    }

    public PersonInMemoryRepository(List<Person> persons) {
        this.persons = persons;
    }

    /**
     * @param id -the id of the entity to be returned id must not be null
     * @return the entity with the specified id or null - if there is no entity with
     * the given id
     */
    @Override
    public Person findOne(Long id) {
        for (Person pers:persons) {
            if (pers.getId() == id){
                return pers;
            }
        }
        return null;
    }

    /**
     * @return all entities
     */
    @Override
    public List<Person> findAll() {
        return persons;
    }

    /**
     * @param pers entity must be not null
     * @return null- if the given entity is saved otherwise returns the entity (id
     * already exists)
     */

    @Override
    public Person save(Person pers) {
        if(pers == null){
            return null;
        }
        for (Person p:persons) {
            if(p.equals(pers)){
                return null;
            }
        }
        persons.add(pers);
        return pers;
    }

    /**
     * removes the entity with the specified id
     *
     * @param id id must be not null
     * @return the removed entity or null if there is no entity with the given id
     */
    @Override
    public Person delete(Long id) {
        Person p = findOne(id);
        persons.remove(p);
        return p;
    }

    /**
     * @param pers entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity - (e.g id
     * does not exist).
     */

    @Override
    public Person update(Person pers) {
        if(pers.equals(findOne(pers.getId()))){
            return  null;
        }
        persons.remove(findOne(pers.getId()));
        persons.add(pers);
        return pers;
    }
}
