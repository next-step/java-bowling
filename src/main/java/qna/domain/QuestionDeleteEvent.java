package qna.domain;

import java.util.Objects;

public class QuestionDeleteEvent {
    private Question question;

    public QuestionDeleteEvent(Question question) {
        this.question = question;
    }

    public DeleteHistory getDeletedHistory() {
        return question.createDeleteHistory();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDeleteEvent that = (QuestionDeleteEvent) o;
        return Objects.equals(question, that.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question);
    }
}
