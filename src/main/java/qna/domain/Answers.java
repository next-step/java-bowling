package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public DeleteHistories deleteAnswers(User loginUser) throws CannotDeleteException {
        DeleteHistories deleteHistories = new DeleteHistories();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return deleteHistories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }
}
