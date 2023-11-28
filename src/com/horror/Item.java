package com.horror;

public class Item implements Lookable {
    private String name;
    private String description;
    private String lookAtDescription;
    private boolean hidden;
    
    public Item() {
        this("Default Name", "Default Description", false);
    }
    
    public Item(String name, String description, boolean hidden) {
        setName(name);
        setDescription(description);
        setHidden(hidden);
    }
    
    @Override
    public void lookAt() {
        if(lookAtDescription == null) {
            System.out.println(description);
        } else {
            System.out.println(lookAtDescription);
        }
    }
    
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
    
    public boolean isHidden() {
        return hidden;
    }
    
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    
    @Override
    public String toString() {
        return String.format("%s: name=%s, description=%s, hidden=%s",
                getClass().getSimpleName(),
                getName(),
                getDescription(),
                isHidden());
    }
}