package com.example.examplefbl;

public class ItemModel {
    String image;
    String heading, desc;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ItemModel(String image, String heading, String desc) {
        this.image = image;
        this.heading = heading;
        this.desc = desc;
    }
}
