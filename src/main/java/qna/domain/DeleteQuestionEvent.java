package qna.domain;

public class DeleteQuestionEvent {
    private final Question question;

    public DeleteQuestionEvent(final Question question) {
        this.question = question;
    }

    public Question getQuestion() {
        return question;
    }
}
