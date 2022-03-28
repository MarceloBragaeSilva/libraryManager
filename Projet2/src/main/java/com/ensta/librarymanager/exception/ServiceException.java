package com.ensta.librarymanager.exception;

public class ServiceException extends Throwable{

    public ServiceException(){
        super("une erreur s'est produite dans le Service");
    }
}
