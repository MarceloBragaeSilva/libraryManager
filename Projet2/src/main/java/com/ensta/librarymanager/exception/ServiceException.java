package com.ensta.librarymanager.exception;

public class ServiceException extends Exception{

    public ServiceException(){
        super("une erreur s'est produite dans le Service");
    }

    public ServiceException(String msg) {
        super(msg);
    }
}
