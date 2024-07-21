package com.amatsolutions.samples.taskmanager.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ChatCompletionResponse {
    private List<Choice> choices;

    public static class Choice {
        private Message message;

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        @Setter
        @Getter
        public static class Message {
            private String role;
            private String content;

        }
    }
}
