package repository;

import model.Employee;
import model.Person;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PersonFileRepository implements CrudRepository<Person>{

    private File file;



    public PersonFileRepository(){
        file = new File("C:\\Users\\Teo\\IdeaProjects\\Lab3_2\\src\\persons.txt");
        if(file.exists()==false)
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else{
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    /**
     * @param id -the id of the entity to be returned id must not be null
     * @return the entity with the specified id or null - if there is no entity with
     * the given id
     *
     *
     */
    @Override
    public Person findOne(Long id) {

        Scanner scan;
        try {
            scan = new Scanner(file);
            while(scan.hasNextLine()){
                String s = scan.nextLine();
                if(Long.parseLong(s.split(",")[0])==id){
                    scan.close();
                    return  Employee.toEmployee(s);
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return  null;
    }

    /**
     * @return all entities
     */
    @Override
    public Iterable<Person> findAll() {
        List<Person> employees = new ArrayList<Person>();
        Scanner scan;
        try {
            scan = new Scanner(file);
            while(scan.hasNextLine()){
                String s = scan.nextLine();
                employees.add(Employee.toEmployee(s));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  employees;

    }

    /**
     * @param entity entity must be not null
     * @return null- if the given entity is saved otherwise returns the entity (id
     * already exists)
     */



    @Override

    public Person save(Person entity) {

        if(entity!=null){
            try {
                FileWriter fWriter = new FileWriter(file,true);
                BufferedWriter bWriter = new BufferedWriter(fWriter);
                PrintWriter pWriter = new PrintWriter(bWriter);
                Employee e = (Employee)entity;
                pWriter.println( e.toStringFile());
                pWriter.close();
                bWriter.close();
                fWriter.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }


        }

        return entity;

    }



    /**
     * removes the entity with the specified id
     *
     * @param id id must be not null
     * @return the removed entity or null if there is no entity with the given id
     */
    @Override
    public Person delete(Long id) {
        Employee employee = null;
        if(id!=0){
            try {

                File temp = new File("temp.txt");
                if(temp.exists()==false)
                {
                    temp.createNewFile();
                }
                Scanner scan = new Scanner(file);
                PrintWriter pw = new PrintWriter(temp);
                while(scan.hasNextLine()){
                    String s = scan.nextLine();
                    if(Long.parseLong(s.split(",")[0])==id){
                        employee = Employee.toEmployee(s);
                        continue;
                    }
                    pw.println(s);
                }

                scan.close();
                pw.flush();
                pw.close();
                file.delete();
                temp.renameTo(new File("C:\\Users\\Teo\\IdeaProjects\\Lab3_2\\src\\persons.txt"));
                file = new File("C:\\Users\\Teo\\IdeaProjects\\Lab3_2\\src\\persons.txt");

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return employee;
    }

    /**
     * @param entity entity must not be null
     * @return null - if the entity is updated, otherwise returns the entity - (e.g id
     * does not exist).
     */
    @Override
    public Person update(Person entity) {
        Employee employee = null;
        if(entity.getId()!=0){
            try {
                File temp = new File("temp.txt");
                if(temp.exists()==false)
                {
                    temp.createNewFile();
                }
                Scanner scan = new Scanner(file);
                PrintWriter pWriter = new PrintWriter(temp);

                while(scan.hasNextLine()){
                    String s = scan.nextLine();

                    if(Long.parseLong(s.split(",")[0])==entity.getId()){
                        employee = (Employee)entity;
                        pWriter.println(employee.toStringFile());
                        continue;
                    }
                    pWriter.println(s);
                }

                scan.close();
                pWriter.flush();
                pWriter.close();

                file.delete();
                temp.renameTo(new File("C:\\Users\\Teo\\IdeaProjects\\Lab3_2\\src\\persons.txt"));
                file = new File("C:\\Users\\Teo\\IdeaProjects\\Lab3_2\\src\\persons.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        return employee;
    }
}
