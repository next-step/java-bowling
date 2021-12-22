package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public Answers() {

    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(this.answers);
    }

    public DeleteHistories delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deletedAnswerHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deletedAnswerHistories.add(answer.delete(loginUser));
        }
        return new DeleteHistories(deletedAnswerHistories);
    }

    public Answers append(Answer answer) {
        answers.add(answer);
        return new Answers(answers);
    }

    public boolean isDeleted() {
        return answers.stream()
                .allMatch(Answer::isDeleted);
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
