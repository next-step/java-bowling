package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id asc")
    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void checkOwner(User loginUser) throws CannotDeleteException {
        for(Answer answer : answers) {
            answer.checkOwner(loginUser);
        };
    }

    public void delete(List<DeleteHistory> deleteHistories) {
        answers.stream().forEachOrdered(answer -> answer.delete(deleteHistories));
    }

}
