package models;

import exceptions.PreviousVersionNotAvailableException;

import java.util.ArrayList;
import java.util.List;

public class Document {
    private final int documentId;
    private String content;
    private final User author;

    private List<String> versions;

    public Document(int documentId,String content,User author) {
        this.documentId = documentId;
        this.content=content;
        this.author=author;
        this.versions=new ArrayList<>();
        this.versions.add(content);
    }


    //getters
    public int getDocumentId(){
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

    //setters

    public void updateContent(String newContent){

        this.content=newContent;
        this.versions.add(content);

    }


}
