package pojoClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateRepoPojo {
    private String name;
    private String description;
    private String homepage;
    @JsonProperty("private")   // maps JSON "private" to this Java field
    private Boolean isPrivate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }
}
