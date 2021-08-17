package com.obss.jss.model.content;

public class ElearningContent extends Content {
    private String name;
    private Long id;

    public ElearningContent(){

    }
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {

        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void calculateSuccess() {


    }
}
