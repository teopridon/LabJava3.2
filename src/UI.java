import controller.PersonAlreadyExistsException;
import controller.PersonController;
import controller.PersonNotExistsException;
import model.Employee;
import model.Person;
import repository.PersonFileRepository;
import repository.PersonInMemoryRepository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import static java.lang.String.valueOf;

public class UI {



    public static void menu(){



        PersonFileRepository personsInFile = new PersonFileRepository();

        Person person1 = new Employee(1,"Peter","Johnson","Klausenburg",Employee.JobPosition.Developer,3,12.5,8);
        Person person2 = new Employee(2,"Maria","Meyer","Munich",Employee.JobPosition.Developer,2,14.5,6);
        Person person3 = new Employee(3,"Georg","Yuruz","Stuttgart",Employee.JobPosition.Tester,1,7.5,8);
        Person person4 = new Employee(4,"Marcel","Hewitt","London",Employee.JobPosition.Teamleader,4,20,8);
        Person person5 = new Employee(5,"Nicole","Schwarz","Paris",Employee.JobPosition.ProjectManager,1,18.5,8);

        personsInFile.save(person1);
        personsInFile.save(person2);
        personsInFile.save(person3);
        personsInFile.save(person4);
        personsInFile.save(person5);






        PersonInMemoryRepository persons = new PersonInMemoryRepository();


        Person pers1 = new Employee(1,"Coco","Man","ffs nr.8",Employee.JobPosition.Developer, 4, 40.0, 6  );

        Person pers2 = new Employee(2,"Claudiu","Antohi","ggh nr.7", Employee.JobPosition.Tester, 3, 35.0, 4 );

        Person pers3 = new Employee(3,"Maria","Ionescu","dss nr.6", Employee.JobPosition.Teamleader, 5, 60.0, 8);

        Person pers4 = new Employee(4,"Ana","Popescu","fdd nr.10", Employee.JobPosition.ProjectManager, 5, 80.0, 8);



        persons.save(pers1);
        persons.save(pers2);
        persons.save(pers3);
        persons.save(pers4);

        System.out.println("Choose 1 in order to work with a file and 2 to work with the In-Memory list");
        Scanner sc = new Scanner(System.in);
        System.out.println("type here:  ");
        int i = sc.nextInt();

        PersonController pc = null;
        switch(i){
            case 1:
                pc = new PersonController(personsInFile);
                break;
            case 2:
                pc = new PersonController(persons);
                break;
        }


        int k = 1;
        while(k == 1) {
            System.out.println("Type the number of the operation you choose to run: \n" +
                    "1. Find a person by id \n" +
                    "2. Show all registered persons \n" +
                    "3. Register/save a person \n" +
                    "4. Modify the personal data of an registered person \n" +
                    "5. Delete a person from the register \n" +
                    "6. Calculate the salary of a employee \n" +
                    "7. Calculate the salary of all employees for a year");


            Scanner keyboard = new Scanner(System.in);
            System.out.println("type here:  ");
            int nr = keyboard.nextInt();



            switch (nr) {
                case 1:
                    System.out.println("Insert the id of the person you want to search for: ");
                    int id = keyboard.nextInt();

                    try {
                        System.out.println(pc.findPersonById(id).toString());
                    } catch (PersonNotExistsException e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    System.out.println(pc.findAll().toString());
                    break;

                case 3:
                    System.out.println("Insert the personal data of the person/employee you want to register: ");

                    System.out.println("Id = ");
                    id = keyboard.nextInt();

                    Scanner input = new Scanner(System.in);
                    System.out.print("FirstName = ");
                    String fn = input.nextLine();

                    input = new Scanner(System.in);
                    System.out.print("LastName = ");
                    String ln = input.nextLine();

                    input = new Scanner(System.in);
                    System.out.print("Address = ");
                    String adr = input.nextLine();


                    Employee.JobPosition jp = null;
                    input = new Scanner(System.in);
                    System.out.print("Enter 1 for JobPosition  Developer\n      2 for Tester\n      3 for ProjectManager\n      4 for TeamLeader\n");
                    i = keyboard.nextInt();
                    switch (i) {
                        case 1:
                            jp = Employee.JobPosition.Developer;
                        case 2:
                            jp = Employee.JobPosition.Tester;
                        case 3:
                            jp = Employee.JobPosition.ProjectManager;
                        case 4:
                            jp = Employee.JobPosition.Teamleader;

                    }


                    System.out.println("LevelOfExpertise = ");
                    int lexp = keyboard.nextInt();

                    System.out.println("HourlyPayment = ");
                    double hp = keyboard.nextDouble();

                    System.out.println("HoursPerDay = ");
                    int hpd = keyboard.nextInt();

                    Employee pers = new Employee(id, fn, ln, adr, jp, lexp, hp, hpd);
                    try {
                        pc.savePerson(pers);
                    } catch (PersonAlreadyExistsException e) {
                        e.printStackTrace();
                    }
                    System.out.println(pc.findAll().toString());
                    break;

                case 4:

                    System.out.println("Enter the data of the modified person: \n");

                    System.out.println("Id = ");
                    id = keyboard.nextInt();

                    input = new Scanner(System.in);
                    System.out.print("FirstName = ");
                    fn = input.nextLine();

                    input = new Scanner(System.in);
                    System.out.print("LastName = ");
                    ln = input.nextLine();

                    input = new Scanner(System.in);
                    System.out.print("Address = ");
                    adr = input.nextLine();


                    jp = null;

                    System.out.print("Enter 1 for JobPosition  Developer\n      2 for Tester\n      3 for ProjectManager\n      4 for TeamLeader\n");
                    i = keyboard.nextInt();
                    switch (i) {
                        case 1:
                            jp = Employee.JobPosition.Developer;break;
                        case 2:
                            jp = Employee.JobPosition.Tester;break;
                        case 3:
                            jp = Employee.JobPosition.ProjectManager;break;
                        case 4:
                            jp = Employee.JobPosition.Teamleader;

                    }


                    System.out.println("LevelOfExpertise = ");
                    lexp = keyboard.nextInt();

                    System.out.println("HourlyPayment = ");
                    hp = keyboard.nextDouble();

                    System.out.println("HoursPerDay = ");
                    hpd = keyboard.nextInt();

                    pers = new Employee(id, fn, ln, adr, jp, lexp, hp, hpd);
                    try {
                        pc.updatePerson(pers);
                    } catch (PersonNotExistsException e) {
                        e.printStackTrace();
                    }

                    System.out.println(pc.findAll().toString());

                    break;

                case 5:
                    System.out.println("Enter the id of the person you want to delete from the register: ");
                    id = keyboard.nextInt();

                    try {
                        pc.deletePerson(pc.findPersonById(id));
                    } catch (PersonNotExistsException e) {
                        e.printStackTrace();
                    }
                    System.out.println(pc.findAll());

                    break;

                case 6:
                    System.out.println("Enter the id of the person for which you want to calculate the salary: ");
                    id = keyboard.nextInt();

                    System.out.println("Enter the number of days for which you want to calculate the salary: ");
                    int nrDays = keyboard.nextInt();

                    try {
                        System.out.println(pc.calculateSalary((Employee) pc.findPersonById(id), nrDays));
                    } catch (PersonNotExistsException e) {
                        e.printStackTrace();
                    }

                    break;

                case 7:
                    if(pc.getPersons() instanceof PersonInMemoryRepository) {
                        for (Person p : persons.findAll()) {

                            try {
                                System.out.println(pc.calculateSalary((Employee) pc.findPersonById(p.getId()), 365));
                            } catch (PersonNotExistsException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    else{

                        System.out.println("Choose:\n1. Salaries for the employees with a certain Job Position\n2. Salaries for all employees\n3. Salaries for employees with a certain level of expertise\n");
                        Scanner sc1 = new Scanner(System.in);
                        System.out.println("type here:  ");
                        int u = sc1.nextInt();
                        jp=null;
                        switch (u)
                        {
                            case 1:
                            {
                                System.out.print("Enter 1 for JobPosition  Developer\n      2 for Tester\n      3 for ProjectManager\n      4 for TeamLeader\n");
                                i = keyboard.nextInt();
                                switch (i) {
                                    case 1:
                                        jp = Employee.JobPosition.Developer;break;
                                    case 2:
                                        jp = Employee.JobPosition.Tester;break;
                                    case 3:
                                        jp = Employee.JobPosition.ProjectManager;break;
                                    case 4:
                                        jp = Employee.JobPosition.Teamleader;

                                }
                            }break;

                            case 3:
                                System.out.println("Choose Level of Expertise:");
                                i = keyboard.nextInt();
                                break;

                        }

                        try {


                            Employee e;


                                    if(u==1)
                                    {
                                       PrintWriter printWriter = new PrintWriter("C:\\Users\\Teo\\IdeaProjects\\Lab3_2\\src\\salaries.txt");
                                       for( Person p : pc.getPersons().findAll())
                                       {
                                           e =(Employee) p;
                                           if(jp==e.getJobPosition())
                                           {
                                               try {
                                                   System.out.println(pc.calculateSalary((Employee) pc.findPersonById(p.getId()), 365));
                                               } catch (PersonNotExistsException e1) {
                                                   e1.printStackTrace();
                                               }
                                               printWriter.println(pc.calculateSalary((Employee) pc.findPersonById(p.getId()), 365));

                                           }
                                       }
                                        printWriter.close();

                                    }
                                    else if (u==2)
                                    {

                                        PrintWriter printWriter = new PrintWriter("C:\\Users\\Teo\\IdeaProjects\\Lab3_2\\src\\salaries.txt");
                                        for( Person p : pc.getPersons().findAll())
                                        {

                                                printWriter.println(pc.calculateSalary((Employee) pc.findPersonById(p.getId()), 365));

                                        }
                                        printWriter.close();
                                    }
                                    else
                                    {
                                        PrintWriter printWriter = new PrintWriter("C:\\Users\\Teo\\IdeaProjects\\Lab3_2\\src\\salaries.txt");
                                        for( Person p : pc.getPersons().findAll())
                                        {
                                            e =(Employee) p;
                                            if(e.getLevelOfExperience()==i)
                                            {

                                                printWriter.println(pc.calculateSalary((Employee) pc.findPersonById(p.getId()), 365));

                                            }
                                        }
                                        printWriter.close();
                                    }




                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (PersonNotExistsException e) {
                            e.printStackTrace();
                        }

                    }

                    break;

            }

            System.out.println("Do you wish to perform another operation?\n 1. Yes 0.No");
            k = keyboard.nextInt();


        }
    }

}
