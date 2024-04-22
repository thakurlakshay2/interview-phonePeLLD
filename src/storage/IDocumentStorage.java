package storage;

import models.Document;
import models.User;

public interface IDocumentStorage {

    public  void addDocument(Document document, User user);

    public Document getDocument(int documentId,User user);

    public boolean getViewDocumentAccess(int documentId,User user);

    public void deleteDocument(int documentId);

    public boolean getEditDocumentAccess(int documentId,User user);

    public void addViewAccess(int documentId,User recepiant);
    public void addEditAccess(int documentId,User recepiant);
}
