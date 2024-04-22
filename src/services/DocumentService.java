package services;

import exceptions.*;
import models.AccessType;
import models.Document;
import models.User;
import storage.DocumentStorage;
import storage.IDocumentStorage;
import storage.UserStorage;

import javax.print.Doc;
import java.util.UUID;


//implementation of some kind of middleware

// additional functionality edit  functions are there but implementation not complete
public class DocumentService {
    IDocumentStorage documentStorage;
    IUserService userService; //service dependency injection / rather than dependent on user storage..

    public DocumentService(IDocumentStorage documentStorage,IUserService userService) {
        this.documentStorage = documentStorage;
        this.userService=userService;
    }

    //get document does not need nahi, tha..
    //user not available exception case not included
    public String getDocument(String  documentId,User user) {
        Document document=documentStorage.getDocument(documentId,user);
        if(document==null){
            throw new NoDocumentException();
        }
        if(!documentStorage.getDocumentAccess(documentId,user,AccessType.VIEW)){
            throw new NoViewAccessException();
        }


        return documentStorage.getContent(documentId);
    }

    // Q : repetation of adding user as author in document storage.
    public String createDocument(String documentContent, User user){
        String documentId= UUID.randomUUID().toString();
        documentStorage.addDocument(new Document( documentId,documentContent,user),user);
        return documentId;
    }
    public synchronized void  updateDocument(String documentId, User user, String updatedContent){
        Document document=documentStorage.getDocument(documentId,user);
        if(document==null){
            throw new NoDocumentException();
        }

        //edit access functionality not complete; , check if user has edit access
        if(!document.getAuthor().getUserName().equals(user.getUserName())){
            throw new NoEditAccessException();
        }

        documentStorage.updateDocumentContent(documentId,updatedContent);

    }

    public void deleteDocument(String documentId,User user){
        Document document=documentStorage.getDocument(documentId,user);
        if(document==null){
            throw new NoDocumentException();
        }

        if(!document.getAuthor().getUserName().equals(user.getUserName())){
            throw new NoDeleteAccessException();
        }
        documentStorage.deleteDocument(documentId);

    }
    public String revertToVersion(String documentId,User user){
        Document document=documentStorage.getDocument(documentId,user);
        if(document==null){
            throw new NoDocumentException();
        }
        if(!document.getAuthor().getUserName().equals(user.getUserName())){
            throw new NoRevertAccessException();
        }

        return documentStorage.revertToPreviousVersion(documentId);
    }

    public void shareDocumentAccess(String documentId, User author, User precipitant, AccessType accessType){
        Document document=documentStorage.getDocument(documentId,author);
        if(document==null){
            throw new NoDocumentException();
        }
        if(!document.getAuthor().getUserName().equals(author.getUserName())){
            throw new NoEditAccessException();   // wrong exception included.
        }
        documentStorage.addAccess(documentId,precipitant,accessType);
    }



}
