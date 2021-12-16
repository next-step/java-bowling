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
    private List<Answer> answers = new ArrayList<>();

    public Answers() {}

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public void checkOwnerForDelete(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.checkOwnerForDelete(loginUser);
        }
    }

    public void deleteAll() {
        answers.forEach(Answer::delete);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> value() {
        return Collections.unmodifiableList(answers);
    }
}
