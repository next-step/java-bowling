package qna.domain;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import org.hibernate.annotations.Where;
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

    protected Answers() {

    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isAnswerEmptyByLoginUser(User loginUser) {
        return answers.stream().allMatch(answer -> answer.isOwner(loginUser));
    }

    public void deleteAnswer(DeleteHistories deleteHistories) {
        for (Answer answer : answers) {
            answer.delete(deleteHistories);
        }
    }
}
