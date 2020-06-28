package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers.addAll(answers);
    }

    public void validate(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validate(loginUser);
        }
    }

    public List<DeleteHistory> delete(List<DeleteHistory> deleteHistories) {
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete());
        }
        return deleteHistories;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }
}
