package qna.domain.entity;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;
import qna.domain.ContentType;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Answers {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answers() {}

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {

        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            answer.delete(loginUser);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }

        return deleteHistories;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isDeleted() {
        return answers.stream().allMatch(Answer::isDeleted);
    }
}
