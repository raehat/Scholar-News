package com.example.examplefbl;

public class datamodel_userSaved {
    String image;
    String header,desc;

    public datamodel_userSaved(String image, String header, String desc) {
        this.image = image;
        this.header = header;
        this.desc = desc;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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

