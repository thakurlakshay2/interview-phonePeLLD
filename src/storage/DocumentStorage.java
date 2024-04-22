package storage;

import exceptions.AccessAlreadyGiven;
import exceptions.AccessCannotBeGranter;
import models.AccessType;
import models.Document;
import models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocumentStorage implements  IDocumentStorage {
    //HashMap< docId , Document >
    HashMap<String, Document> doocumentMap;

    //HashMap < documentId , HashMap<User,AccessType>
    HashMap<String, HashMap<String, List<AccessType>> >  documentAccessMap; //
    public DocumentStorage() {
        this.doocumentMap = new HashMap<>();
        this.documentAccessMap=new HashMap<>();
    }

    public  void addDocument(Document document, User user){
        doocumentMap.put(document.getDocumentId(),document);
        HashMap<String, List<AccessType>> accessMap =new HashMap<>();
        ArrayList<AccessType> accessTypeGranted=new ArrayList<>();
        accessTypeGranted.add(AccessType.ALL);
        accessTypeGranted.add(AccessType.EDIT);
        accessTypeGranted.add(AccessType.VIEW);
        accessMap.put(user.getUserName(),accessTypeGranted);
        documentAccessMap.put(document.getDocumentId(),accessMap);
    }


    public Document getDocument(String documentId,User user){
        return doocumentMap.get(documentId);
    }
    public void deleteDocument(String documentId){
        doocumentMap.remove(documentId);
        documentAccessMap.remove(documentId);
    }

    @Override
    public String getContent(String documentId) {
        Document document=doocumentMap.get(documentId);
        return document.getContent();

    }

    public boolean getDocumentAccess(String documentId, User user,AccessType accessType){
        if(documentAccessMap.get(documentId).get(user.getUserName()).contains(accessType)){
            return true;
        }
        return false;
    }

    @Override
    public String revertToPreviousVersion(String documentId) {
        Document document=doocumentMap.get(documentId);
        return document.revertToPrevVersion();
    }

    @Override
    public void updateDocumentContent(String documentId, String updatedContent) {
        Document document=doocumentMap.get(documentId);
         document.updateContent(updatedContent);
    }



    public void addAccess(String documentId, User precipitant , AccessType accessType){

        HashMap<String, List<AccessType>>  listOfAccessMapForDocument=  documentAccessMap.get(documentId);
        List<AccessType> accessAlreadyGiven=listOfAccessMapForDocument.getOrDefault(precipitant.getUserName(),new ArrayList<>());
        if(isNewAccessValid(accessType,accessAlreadyGiven)){
            accessAlreadyGiven.add(accessType);
            listOfAccessMapForDocument.put(precipitant.getUserName(),accessAlreadyGiven);
        }else{
            throw new AccessCannotBeGranter();
        }

    }

    private final boolean isNewAccessValid(AccessType newAccess, List<AccessType> accessAlreadyGiven){
        if(accessAlreadyGiven.contains(newAccess)){
            throw new AccessAlreadyGiven();
        }

        return true;

    }


}
