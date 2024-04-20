package services;

import exceptions.NoSuchUserException;
import exceptions.PasswordIncorrect;
import exceptions.UserAlreadyExistsException;
import models.User;

public interface IUserService {

    public void createUser(String userName, String pass) throws  UserAlreadyExistsException;
    public User loginUser(String userName,String userPassword) throws  NoSuchUserException,PasswordIncorrect;
}
