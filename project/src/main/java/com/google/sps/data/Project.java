package com.google.sps.data;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;

public class Project {

    private String title;
    private String summary;
	private Set<String> tags;
	//private User advisor;
	//private Set<User> group; 
	private LocalDateTime dateCreated;

    public Project(String title) {
        dateCreated = LocalDateTime.now();
        this.title = title;
        this.summary = "";
        this.tags = new HashSet<>();
    }

    @Override
    public String toString(){
        return title + ": " + summary + " (" + String.join(", ", tags) + ")";
    }

    public String getTitle(){
        return this.title;
    }

    public String getSummary(){
        return this.summary;
    }

    public Set<String> getTags(){
        return this.tags;
    }

    public LocalDateTime getDateCreated(){
        return this.dateCreated;
    }

    public void setTitle(String newTitle){
        this.title = newTitle;
    }

    public void setSummary(String newSummary){
        this.summary = newSummary;
    }

    public void setTags(Set<String> tags){
        for (String tag : tags){
            this.tags.add(tag.trim().toLowerCase());
        }
    }

    public boolean addTag(String tag){
        return tags.add(tag.trim().toLowerCase());
    }

    public boolean removeTag(String tag){
        return tags.remove(tag.trim().toLowerCase());
    }

}

