package com.pfe.pfe;

import java.util.List;

public class Question {
    private String name;
    private String style;
    private List<Respense> respense;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setStyle(String type) {
        this.style = type;
    }

    public void setRespense(List<Respense> respense) {
        this.respense = respense;
    }

    public List<Respense> getRespense() {
        return respense;
    }

    public String getStyle() {
        return style;
    }


    public Question() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
