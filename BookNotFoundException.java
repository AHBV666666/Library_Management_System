package Library_Management_System;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String message){
        super(message);
    }
}