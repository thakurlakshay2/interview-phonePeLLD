import models.AccessType;
import models.Document;
import models.User;
import services.DocumentService;
import services.IUserService;
import services.UserService;
import storage.DocumentStorage;
import storage.IDocumentStorage;
import storage.IUserStorage;
import storage.UserStorage;


//improvement  - document access type - private ,  public.  //additional functionality.


//Q : what happens if multiple used add data at same time..
public class Main {
    public static void main(String[] args) {
        IDocumentStorage documentStorage=new DocumentStorage();
        IUserStorage userStorage=new UserStorage();

        IUserService userService=new UserService(userStorage);
        DocumentService documentService=new DocumentService(documentStorage,userService);

        userService.createUser("user1","123");
        userService.createUser("user2","345");

        User user1=userService.loginUser("user1","123");

        System.out.println(user1.getUserName());

//        User user2=userService.loginUser("user2","1234");  //wrong password
//        System.out.println(user1.getUserName());

            User user2=userService.loginUser("user2","345");

        String documentId= documentService.createDocument("user 1 first content",user1);
//        documentService.createDocument("user 2 first content",user2,1); //exception


        //1. getDocument
        String documentContent=documentService.getDocument(documentId,user1);

        System.out.println(documentContent);

        //2. UpdateDocument
        documentService.updateDocument(documentId,user1,"user 1 second content");

        //getDocument after update
        String upatedContent=documentService.getDocument(documentId,user1);

        System.out.println(upatedContent);

        //User 2 cannot update the content given by user 1

        //revert to previous version


        //3. Revert to previous version
        documentService.revertToVersion(documentId,user1);

        String revertedContent=documentService.getDocument(documentId,user1);

        System.out.println(revertedContent);

//        documentService.revertToVersion(1,user1);   //exception

        //4. Delete Document
        documentService.deleteDocument(documentId,user1);

            //get Document after delete
//        String deletedContent=documentService.getDocument(1);  //Exception no document found



        //ADDITIONAL

        String documentId2=documentService.createDocument("user 1 first content",user1);

//        documentService.getDocument(1,user2); //Exception


        documentService.shareDocumentAccess(documentId2,user1,user2, AccessType.VIEW);
        String  viewAc=documentService.getDocument(documentId2,user2);
        System.out.println(viewAc);
    }
}