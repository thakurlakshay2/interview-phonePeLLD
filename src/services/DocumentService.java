package services;

import exceptions.*;
import models.Document;
import models.User;
import storage.DocumentStorage;
import storage.UserStorage;

import javax.print.Doc;

public class DocumentService {
    DocumentStorage documentStorage;
    IUserService userService;

    public DocumentService(DocumentStorage documentStorage,IUserService userService) {
        this.documentStorage = documentStorage;
        this.userService=userService;
    }

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

    public void createDocument(String documentContent, User user, int documentId){
            if(documentStorage.getDocument(documentId,user)!=null){
                throw new DocumentAlreadyExistsException();
            }
            documentStorage.addDocument(new Document(documentId,documentContent,user),user);
    }
    public void updateDocument(int documentId, User user, String content){
        Document document=documentStorage.getDocument(documentId,user);
        if(document==null){
            throw new NoDocumentException();
        }
        if(!document.getAuthor().getUserName().equals(user.getUserName())){
            throw new NoEditAccessException();
        }

        document.updateContent(content);

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
            throw new NoRevertAccessException();
        }
        documentStorage.addViewAccess(documentId,recepiant);
    }

//    private final checkDocumentValidity(){
//
//    }
}
