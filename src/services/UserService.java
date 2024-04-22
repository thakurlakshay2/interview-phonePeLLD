package services;

import exceptions.NoSuchUserException;
import exceptions.PasswordIncorrect;
import exceptions.UserAlreadyExistsException;
import models.User;
import storage.IUserStorage;
import storage.UserStorage;


public class UserService implements  IUserService {
    IUserStorage userStorage;

    public UserService(IUserStorage userStorage) {
        this.userStorage = userStorage;
    }

    //Q : user id is useless.
    public void createUser(String userName, String pass){
        if(userStorage.getUser(userName)!=null){
            throw new UserAlreadyExistsException();
        }
        userStorage.addUser(new User(userName,1, pass));
    }

    // null patten  should have been used
    public User loginUser(String userName,String userPassword){
        User user= userStorage.getUser(userName);
        if(user==null){
            throw new NoSuchUserException();
        }

        if(!user.getPassword().equals(userPassword)){
          throw new PasswordIncorrect();
        }
        return user;
    }
}
