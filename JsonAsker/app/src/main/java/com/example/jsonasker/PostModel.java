
//-----------------------------------com.example.PostModel.java-----------------------------------

        package com.example.jsonasker;

        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class PostModel {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("properties")
    @Expose
    private Properties properties;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

}
