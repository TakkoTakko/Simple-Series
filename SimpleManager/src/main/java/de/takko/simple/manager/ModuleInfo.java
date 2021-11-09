package de.takko.simple.manager;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ModuleInfo {

    @SerializedName("name")
    private String name;
    @SerializedName("version")
    private String version;
    @SerializedName("authors")
    private String[] authors;
    @SerializedName("description")
    private String description;
    @SerializedName("main")
    private String mainClass;
    @SerializedName("commands")
    private String[] commands;

}