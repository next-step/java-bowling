package qna.domain;

public class QuestionDeleteEvent {
    private Question question;

    public QuestionDeleteEvent(Question question) {
        this.question = question;
    }

    public DeleteHistory getDeletedHistory() {
        return question.createDeleteHistory();
    }
}
