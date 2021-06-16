package qna.domain;

import org.hibernate.annotations.Where;
import qna.exception.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static qna.exception.CannotDeleteException.QUESTION_WITH_OTHERS_ANSWER;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public DeleteHistories deleteAllAnswers(User loginUser) {
        DeleteHistories deleteHistories = new DeleteHistories();
        validateDeletableAnswers(loginUser);
        for (Answer answer : answers) {
            deleteHistories.add(answer.deleteAnswer(loginUser));
        }
        return deleteHistories;
    }

    private void validateDeletableAnswers(User loginUser) {
        for (Answer answer : answers) {
            validateDeletableAnswer(answer, loginUser);
        }
    }

    private void validateDeletableAnswer(Answer answer, User loginUser) {
        if (!answer.isDeletable(loginUser)) {
            throw new CannotDeleteException(QUESTION_WITH_OTHERS_ANSWER);
        }
    }

    public int size() {
        return answers.size();
    }

    public List<Answer> answers() {
        return answers;
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
