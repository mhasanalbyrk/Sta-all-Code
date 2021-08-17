package com.obss.jss.model.content;

public abstract class Content {
    protected String name;
    protected Long id;
    protected String publishDate;

    protected String endDate;

    protected double successRatio;

    public abstract void calculateSuccess();

    public abstract Long getId();

    public abstract void setId(Long id);

    public abstract String getName();


}
