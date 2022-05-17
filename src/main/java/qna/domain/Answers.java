package qna.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public final class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public DeleteHistories delete() {
        DeleteHistories deleteHistories = new DeleteHistories();
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.id, answer.getWriter(), LocalDateTime.now()));
        }
        return deleteHistories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answers answers1 = (Answers) o;
        return Objects.equals(answers, answers1.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answers);
    }


}
