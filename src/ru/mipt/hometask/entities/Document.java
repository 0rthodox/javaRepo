package ru.mipt.hometask.entities;

public class Document {
    private static int currentId;

    protected int id;
    protected String documentName;
    protected String documentOwner;
    protected Long documentId;

    static {
        currentId = 1;
    }

    public Document(int id, String documentName, String documentOwner, Long documentId) {
        this.id = id;
        this.documentName = documentName;
        this.documentOwner = documentOwner;
        this.documentId = documentId;
    }

    public Document(String documentName, String documentOwner, Long documentId) {
        this.id = getCurrentId();
        this.documentName = documentName;
        this.documentOwner = documentOwner;
        this.documentId = documentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentOwner() {
        return documentOwner;
    }

    public void setDocumentOwner(String documentOwner) {
        this.documentOwner = documentOwner;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public static int getCurrentId() {
        return currentId++;
    }
}
