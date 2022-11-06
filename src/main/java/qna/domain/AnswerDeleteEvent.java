package qna.domain;

public class AnswerDeleteEvent {
    private Answer answer;

    public AnswerDeleteEvent(Answer answer) {
        this.answer = answer;
    }

    public DeleteHistory getDeletedHistory() {
        return answer.createDeleteHistory();
    }
}
