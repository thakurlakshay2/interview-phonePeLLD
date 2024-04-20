package services;

import exceptions.NoSuchUserException;
import exceptions.PasswordIncorrect;
import exceptions.UserAlreadyExistsException;
import models.User;
import storage.UserStorage;


public class UserService {
    UserStorage userStorage;

    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public void createUser(String userName, String pass){
        if(userStorage.getUser(userName)!=null){
            throw new UserAlreadyExistsException();
        }
        userStorage.addUser(new User(userName,1, pass));
    }
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
