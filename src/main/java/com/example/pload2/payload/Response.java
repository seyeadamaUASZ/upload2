package com.example.pload2.payload;

public class Response {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;

    public Response(String fileName, String fileDownloadUri, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }
 // Getters and Setters (Omitted for brevity)
    public String getFileName(){
        return this.fileName;
    }

     public void setFileName(String fileName){
        this.fileName=fileName;
    }

    public String getFileDownloadUri(){
        return this.fileDownloadUri;
    }

     public void setFileDownloadUri(String fileDownloadUri){
        this.fileDownloadUri=fileDownloadUri;
    }

    public String getFileType(){
        return this.fileType;
    }

    public void setFileType(String fileType){
        this.fileType=fileType;
    }

    public Long getSize(){
        return this.size;
    }

    public void setSize(Long size){
        this.size=size;
    }
}