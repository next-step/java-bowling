package qna.domain;

public enum ContentType {
    QUESTION, ANSWER;

    static ContentType valueOf(Object object) {
        if (object instanceof Answer) {
            return ANSWER;
        }

        if (object instanceof Question) {
            return QUESTION;
        }

        throw new IllegalArgumentException();
    }
}
