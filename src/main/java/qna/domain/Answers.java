package qna.domain;

import qna.CannotDeleteException;
import qna.NotFoundDeleteHistoryException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Embeddable
@Entity
public class Answers extends AbstractEntity {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Answer> values;

    public Answers() {
    }

    public Answers(List<Answer> values) {
        this.values = values;
    }

    public void deleteAll(User loginUser) throws CannotDeleteException {
        for (Answer answer : values) {
            validateOwner(loginUser, answer);
        }

        for (Answer answer : values) {
            answer.delete();
        }
    }

    private void validateOwner(User loginUser, Answer answer) throws CannotDeleteException {
        if (!answer.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public void add(Answer answer) {
        values.add(answer);
    }

    public List<Answer> values() {
        return values;
    }

    public List<DeleteHistory> deletedAnswerHistories() {
        for (Answer answer : values) {
            validateDeleteStatus(answer);
        }
        return values.stream()
                .map(Answer::deleteHistory)
                .collect(Collectors.toList());
    }

    private void validateDeleteStatus(Answer answer) {
        if (!answer.deleted()) {
            throw new NotFoundDeleteHistoryException();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Answers answers = (Answers) o;
        return Objects.equals(values, answers.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), values);
    }
}
