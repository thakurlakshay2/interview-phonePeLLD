package models;

import exceptions.PreviousVersionNotAvailableException;

import java.util.ArrayList;
import java.util.List;

public class Document {
    private final String documentId;
    private String content;
    private final User author;

    private DocumentType documentType;
    private List<String> versions;

    //


    public Document(String documentId,String content,User author) {
        this.documentId = documentId;
        this.content=content;
        this.author=author;
        this.versions=new ArrayList<>();
        this.versions.add(content);
        this.documentType=DocumentType.PRIVATE;
    }

    public Document(String documentId,String content,User author,DocumentType documentType) {
        this.documentId = documentId;
        this.content=content;
        this.author=author;
        this.versions=new ArrayList<>();
        this.versions.add(content);
        this.documentType=documentType;
    }


    //getters
    public String getDocumentId(){
        return this.documentId;
    }


    public String getContent() {
        return this.content;
    }

    public User getAuthor() {
        return this.author;
    }

    public int getLatestActiveVersionOfDocument(){
            return versions.size();
    }
    public String revertToPrevVersion() throws PreviousVersionNotAvailableException{
        if(versions.size()==1){
            throw new PreviousVersionNotAvailableException();
        }
        int size=versions.size();
        String prevVersionContent=versions.get(size-2);
        versions.remove(size-1);
        this.content=prevVersionContent;
        return prevVersionContent;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

//setters

    public void updateContent(String newContent){

        this.content=newContent;
        this.versions.add(content);

    }


}
