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

    public List<DeleteHistory> delete(User writer) {
        final List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (final Answer answer : answers) {
            deleteHistories.add(answer.delete(writer));
        }

        return deleteHistories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> answers() {
        return Collections.unmodifiableList(answers);
    }

}
