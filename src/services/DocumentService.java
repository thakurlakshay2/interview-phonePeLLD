package services;

import exceptions.*;
import models.Document;
import models.User;
import storage.DocumentStorage;
import storage.UserStorage;

import javax.print.Doc;

public class DocumentService {
    DocumentStorage documentStorage;
    UserService userService;

    public DocumentService(DocumentStorage documentStorage,UserService userService) {
        this.documentStorage = documentStorage;
        this.userService=userService;
    }

    public String getDocument(int  documentId){
        Document document=documentStorage.getDocument(documentId);
        if(document==null){
            throw new NoDocumentException();
        }

        return document.getContent();
    }

    public void createDocument(String documentContent, User user, int documentId){
            if(documentStorage.getDocument(documentId)!=null){
                throw new DocumentAlreadyExistsException();
            }
            documentStorage.addDocument(new Document(documentId,documentContent,user),user);
    }
    public void updateDocument(int documentId, User user, String content){
        Document document=documentStorage.getDocument(documentId);
        if(document==null){
            throw new NoDocumentException();
        }
        if(!document.getAuthor().getUserName().equals(user.getUserName())){
            throw new NoEditAccessException();
        }

        document.updateContent(content);

    }

    public void deleteDocument(int documentId,User user){
        Document document=documentStorage.getDocument(documentId);
        if(document==null){
            throw new NoDocumentException();
        }

        if(!document.getAuthor().getUserName().equals(user.getUserName())){
            throw new NoDeleteAccessException();
        }
        documentStorage.deleteDocument(documentId);

    }
    public String revertToVersion(int documentId,User user){
        Document document=documentStorage.getDocument(documentId);
        if(document==null){
            throw new NoDocumentException();
        }
        if(!document.getAuthor().getUserName().equals(user.getUserName())){
            throw new NoRevertAccessException();
        }

        return document.revertToPrevVersion();
    }
}
