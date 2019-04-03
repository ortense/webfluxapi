package com.springboot.webfluxapi.githubrepo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GithubRepo {
    Long id;
    
    String name;
    
    String descripion;
    
    String language;
   
    @JsonProperty("html_url")
    String url;
    
    @JsonProperty("stargazers_count")
    Integer starts;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the descripion
     */
    public String getDescripion() {
        return descripion;
    }

    /**
     * @param descripion the descripion to set
     */
    public void setDescripion(String descripion) {
        this.descripion = descripion;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the starts
     */
    public Integer getStarts() {
        return starts;
    }

    /**
     * @param starts the starts to set
     */
    public void setStarts(Integer starts) {
        this.starts = starts;
    }
}