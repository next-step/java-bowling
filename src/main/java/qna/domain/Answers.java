package qna.domain;

import org.hibernate.annotations.Where;

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


    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isOwner(User loginUser) {
        return answers.stream().noneMatch(answer -> !answer.isOwner(loginUser));
    }

    public void writeDeleteHistories(List<DeleteHistory> deleteHistories) {
        for (Answer answer : answers) {
            answer.delete(answer, deleteHistories);
        }
    }
}
