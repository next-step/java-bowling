package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void canDeleteBy(User loginUser) throws CannotDeleteException {
        for (Answer answer: answers) {
            answer.canDeleteBy(loginUser);
        }
    }

    public DeleteHistories delete() {
        return DeleteHistories.of(
                answers.stream()
                        .map(Answer::delete)
                        .collect(Collectors.toList()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answers answers1 = (Answers) o;

        return answers != null ? answers.equals(answers1.answers) : answers1.answers == null;
    }

    @Override
    public int hashCode() {
        return answers != null ? answers.hashCode() : 0;
    }
}
