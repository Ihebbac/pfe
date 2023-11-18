package com.pfe.pfe;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // asn3ou
@ConfigurationProperties // 3abih
public class ReadConfigFile {
    private List<Question> questions;

    public ReadConfigFile() {
        super();
    }
    @Override
    public String toString() {
        return super.toString();
    }



    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
