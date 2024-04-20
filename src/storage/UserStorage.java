package storage;

import exceptions.UserAlreadyExistsException;
import models.Document;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private Map<String, User> userMap ;
    private Map<String, ArrayList<Document>> userDocumentMap;
    public UserStorage() {
        this.userMap = new HashMap<>();
        this.userDocumentMap=new HashMap<>();
    }

    public User getUser(String userName){
     return    userMap.getOrDefault(userName,null);
    }
    public void addUser(User user) {

        this.userMap.put(user.getUserName(), user);
        this.userDocumentMap.put(user.getUserName(),new ArrayList<Document>());


    }

    public Map<String, User> getUserMap() {
        return this.userMap;
    }



}
