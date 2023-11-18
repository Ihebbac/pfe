package com.pfe.pfe;

public class Respense {
    private String choix;
    private  Integer score;

    public Respense() {
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

    public void setChoix(String name) {
        this.choix = name;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getChoix() {
        return choix;
    }

    public Integer getScore() {
        return score;
    }
}
