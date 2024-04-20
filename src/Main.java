import models.Document;
import models.User;
import services.DocumentService;
import services.UserService;
import storage.DocumentStorage;
import storage.UserStorage;

public class Main {
    public static void main(String[] args) {
        DocumentStorage documentStorage=new DocumentStorage();
        UserStorage userStorage=new UserStorage();

        UserService userService=new UserService(userStorage);
        DocumentService documentService=new DocumentService(documentStorage,userService);

        userService.createUser("user1","123");
        userService.createUser("user2","345");

        User user1=userService.loginUser("user1","123");

//        System.out.println(user1.getUserName());

//        User user2=userService.loginUser("user2","1234");  //wrong password
//        System.out.println(user1.getUserName());
            User user2=userService.loginUser("user2","345");

        documentService.createDocument("user 1 first content",user1,1);
//        documentService.createDocument("user 2 first content",user2,1); //exception


        //1. getDocument
        String documentContent=documentService.getDocument(1);

        //2. UpdateDocument
        documentService.updateDocument(1,user1,"user 1 second content");

        //getDocument after update
        String upatedContent=documentService.getDocument(1);

        System.out.println(upatedContent);

        //User 2 cannot update the content given by user 1

        //revert to previous version


        //3. Revert to previous version
        documentService.revertToVersion(1,user1);

        String revertedContent=documentService.getDocument(1);

        System.out.println(revertedContent);

//        documentService.revertToVersion(1,user1);   //exception

        //4. Delete Document
        documentService.deleteDocument(1,user1);

            //get Document after delete
//        String deletedContent=documentService.getDocument(1);  //Exception no document found



    }
}