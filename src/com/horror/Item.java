package com.horror;

import com.horror.app.Controller;

import java.util.HashSet;
import java.util.Set;

public class Item implements Lookable, Usable {
    private String name;
    private String description;
    private String lookAtDescription;
    private boolean hidden;
    private String hiddenLocation;
    private boolean usable;
    private String canBeUsedOn;

    
    public Item() {
        this("Default Name", "Default Description", false, false, "");
    }
    
    public Item(String name, String description, boolean hidden, boolean usable, String canBeUsedOn) {
        setName(name);
        setDescription(description);
        setHidden(hidden);
        setUsable(usable);
        setCanBeUsedOn(canBeUsedOn);
    }

    @Override
    public UsedOn use() {
        if (usable) {
            Controller.displayHandler.displayTextClearBefore(getName() + "can be used on " + getCanBeUsedOn(), false);
            try {
                return UsedOn.valueOf(canBeUsedOn.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UsedOn.NULL;
            }
        } else {
            return UsedOn.NULL;
        }
    }
    
    @Override
    public String lookAt() {
        if(lookAtDescription == null) {
            return description;
        } else {
            return lookAtDescription;
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
    
    public String getHiddenLocation() {
        return hiddenLocation;
    }

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

    public String getCanBeUsedOn() {
        return canBeUsedOn;
    }

    public void setCanBeUsedOn(String canBeUsedOn) {
        this.canBeUsedOn = canBeUsedOn;
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