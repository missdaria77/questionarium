package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Question {
    private int id;
    private String text;
    private String topic;

    public Question(String text, String topic) {
        this.text = text;
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public String getTopic() {
        return topic;
    }

}
