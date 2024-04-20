package storage;

import models.AccessType;
import models.Document;
import models.User;

import java.util.HashMap;

public class DocumentStorage {
    //HashMap< docId , Document >
    HashMap<Integer, Document> doocumentMap;

    //HashMap < documentId , HashSet<User>
    HashMap<Integer, HashMap<User, AccessType> >  documentAccessMap;
    public DocumentStorage() {
        this.doocumentMap = new HashMap<>();
        this.documentAccessMap=new HashMap<>();
    }

    public  void addDocument(Document document, User user){
        doocumentMap.put(document.getDocumentId(),document);
    }


    public Document getDocument(int documentId){
        return doocumentMap.get(documentId);
    }
    public void deleteDocument(int documentId){
        doocumentMap.remove(documentId);
        documentAccessMap.remove(documentId);
    }

}
