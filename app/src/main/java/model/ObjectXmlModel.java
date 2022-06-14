package model;

import com.google.gson.annotations.SerializedName;

public class ObjectXmlModel {

    @SerializedName("type")
    private String type;

    @SerializedName("height")
    private int height;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
