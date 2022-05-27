package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers;

    public Answers() {
        answers = new ArrayList<>();
    }

    protected List<Answer> answers() {
        return Collections.unmodifiableList(answers);
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void delete(User loginUser, List<DeleteHistory> deleteHistories) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser, deleteHistories);
        }
    }
}
