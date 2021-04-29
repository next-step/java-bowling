package qna.domain;

public enum ContentType {
    QUESTION, ANSWER, POST;

    public static ContentType check(AbstractEntity post) {
        if(post instanceof Question) {
            return QUESTION;
        }
        if (post instanceof Answer) {
            return ANSWER;
        }
        return POST;
    }
}
