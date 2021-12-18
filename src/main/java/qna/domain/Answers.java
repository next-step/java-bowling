package qna.domain;

import org.hibernate.annotations.Where;
import org.springframework.util.CollectionUtils;
import qna.CannotDeleteException;

import javax.persistence.*;
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

    public void delete(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
    }

    public boolean isEmpty() {
        return CollectionUtils.isEmpty(answers);
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

}
