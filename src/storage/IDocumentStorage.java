package storage;

import models.AccessType;
import models.Document;
import models.User;

public interface IDocumentStorage {

    public  void addDocument(Document document, User user);

    public Document getDocument(String documentId,User user);

    public String getContent(String documentId);

    public void deleteDocument(String documentId);


    public String revertToPreviousVersion(String documentId);

    public void updateDocumentContent(String documentId,String updatedContent);
    public boolean getDocumentAccess(String documentId, User user, AccessType accessType); //additional

    public void addAccess(String documentId, User recepiant , AccessType accessType);
}
