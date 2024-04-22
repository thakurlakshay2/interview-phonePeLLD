package services;

import exceptions.*;
import models.Document;
import models.User;
import storage.DocumentStorage;
import storage.IDocumentStorage;
import storage.UserStorage;

import javax.print.Doc;


//implementation of some kind of middleware

// additional functionality edit  functions are there but implementation not complete
public class DocumentService {
    IDocumentStorage documentStorage;
    IUserService userService;

    public DocumentService(IDocumentStorage documentStorage,IUserService userService) {
        this.documentStorage = documentStorage;
        this.userService=userService;
    }

    //get document does not need nahi, tha..
    //user not available exception case not included
    public String getDocument(int  documentId,User user){
        Document document=documentStorage.getDocument(documentId,user);
        if(document==null){
            throw new NoDocumentException();
        }
        if(!documentStorage.getViewDocumentAccess(documentId,user)){
            throw new NoViewAccessException();
        }


        return document.getContent();
    }

    // Q : repetation of adding user as author in document storage.
    public void createDocument(String documentContent, User user, int documentId){
            if(documentStorage.getDocument(documentId,user)!=null){
                throw new DocumentAlreadyExistsException();
            }
            documentStorage.addDocument(new Document(documentId,documentContent,user),user);
    }
    public synchronized void  updateDocument(int documentId, User user, String updatedContent){
        Document document=documentStorage.getDocument(documentId,user);
        if(document==null){
            throw new NoDocumentException();
        }

        //edit access functionality not complete; , check if user has edit access
        if(!document.getAuthor().getUserName().equals(user.getUserName())){
            throw new NoEditAccessException();
        }

        document.updateContent(updatedContent);

    }

    public void deleteDocument(int documentId,User user){
        Document document=documentStorage.getDocument(documentId,user);
        if(document==null){
            throw new NoDocumentException();
        }

        if(!document.getAuthor().getUserName().equals(user.getUserName())){
            throw new NoDeleteAccessException();
        }
        documentStorage.deleteDocument(documentId);

    }
    public String revertToVersion(int documentId,User user){
        Document document=documentStorage.getDocument(documentId,user);
        if(document==null){
            throw new NoDocumentException();
        }
        if(!document.getAuthor().getUserName().equals(user.getUserName())){
            throw new NoRevertAccessException();
        }

        return document.revertToPrevVersion();
    }

    public void shareViewDocumentAccess(int documentId,User author,User recepiant){
        Document document=documentStorage.getDocument(documentId,author);
        if(document==null){
            throw new NoDocumentException();
        }
        if(!document.getAuthor().getUserName().equals(author.getUserName())){
            throw new NoEditAccessException();   // wrong exception included.
        }
        documentStorage.addViewAccess(documentId,recepiant);
    }


}
