package com.obss.jss.model.content;

public class VideoContent extends Content {

    private String name;
    private Long id;

    public VideoContent(){

    }
    public VideoContent(String name) {
        super();
        this.name = name;
    }

    @Override
    public void calculateSuccess() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
