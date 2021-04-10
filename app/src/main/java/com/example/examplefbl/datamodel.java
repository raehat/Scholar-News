package com.example.examplefbl;

public class datamodel {
    int image;
    String header,desc;
    String docId;

    public datamodel(int image, String header, String desc, String docID) {
        this.image = image;
        this.header = header;
        this.desc = desc;
        this.docId= docID;
    }



    public String getDocId() {
        return docId;
    }

    public void  setDocId(String docId) {this.docId= docId;}


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
