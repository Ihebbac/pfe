package com.pfe.pfe;



public class StudentScor {
    private String mail;

    public StudentScor(String mail, Integer score) {
        this.mail = mail;
        this.score = score;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getMail() {
        return mail;
    }

    public Integer getScore() {
        return score;
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



    private Integer score;

}
