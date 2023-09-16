package controller;


public class PersonNotExistsException extends Exception {
    public PersonNotExistsException(){
        super();
    }
    public PersonNotExistsException(String message){
        super(message);
    }
}
