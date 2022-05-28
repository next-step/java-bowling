package qna.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Where;

import qna.CannotDeleteException;

@Embeddable
public class AnswerList {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private final List<Answer> answers = new ArrayList<>();

    public boolean isEmpty() {
        return answers.isEmpty();
    }

    public void add(final Answer answer) {
        answers.add(answer);
    }

    public void deleteAll(final User loginUser) {
        answers.forEach(answer -> answer.delete(loginUser));
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
