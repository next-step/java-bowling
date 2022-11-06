package qna.domain;

import java.util.Objects;

public class AnswerDeleteEvent {
    private Answer answer;

    public AnswerDeleteEvent(Answer answer) {
        this.answer = answer;
    }

    public DeleteHistory getDeletedHistory() {
        return answer.createDeleteHistory();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDeleteEvent that = (AnswerDeleteEvent) o;
        return Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer);
    }
}
