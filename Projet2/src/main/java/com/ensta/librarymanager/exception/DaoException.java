package com.ensta.librarymanager.exception;

public class DaoException extends Throwable{

    public DaoException(){
        super("une erreur s'est produite dans la DAO");
    }

}
