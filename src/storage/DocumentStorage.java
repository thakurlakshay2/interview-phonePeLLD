package storage;

import models.AccessType;
import models.Document;
import models.User;

import java.util.HashMap;

public class DocumentStorage {
    //HashMap< docId , Document >
    HashMap<Integer, Document> doocumentMap;

    //HashMap < documentId , HashSet<User>
    HashMap<Integer, HashMap<String, AccessType> >  documentAccessMap;
    public DocumentStorage() {
        this.doocumentMap = new HashMap<>();
        this.documentAccessMap=new HashMap<>();
    }

    public  void addDocument(Document document, User user){
        doocumentMap.put(document.getDocumentId(),document);

        HashMap<String, AccessType> accessMap =new HashMap<>();
        accessMap.put(user.getUserName(),AccessType.ALL);
        documentAccessMap.put(document.getDocumentId(),accessMap);
    }


    public Document getDocument(int documentId,User user){
        return doocumentMap.get(documentId);
    }
    public void deleteDocument(int documentId){
        doocumentMap.remove(documentId);
        documentAccessMap.remove(documentId);
    }

    public boolean getViewDocumentAccess(int documentId,User user){
        if(documentAccessMap.get(documentId).get(user.getUserName())==null){
            return false;
        }
        return true;
    }

    public boolean getEditDocumentAccess(int documentId,User user){
        if(documentAccessMap.get(documentId).get(user.getUserName())==AccessType.EDIT){
            return true;
        }
        return false;
    }

    public void addViewAccess(int documentId,User recepiant){
        HashMap<String, AccessType>  accesMapFOrDoc=  documentAccessMap.get(documentId);
        accesMapFOrDoc.put(recepiant.getUserName(),AccessType.VIEW);

    }

}
