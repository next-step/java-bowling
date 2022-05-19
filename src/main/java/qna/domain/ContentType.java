package qna.domain;

public enum ContentType {
    QUESTION, ANSWER;

    ContentType valueOf(Object object) {
        if (object instanceof Answer) {
            return ANSWER;
        }

        if (object instanceof Question) {
            return QUESTION;
        }

        throw new IllegalArgumentException();
    }
}
