package qna.domain.qna.answer;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;
import qna.domain.deletehistory.DeleteHistory;
import qna.domain.user.User;

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

    public Answers() {
    }

    public Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(answer.delete(loginUser));
        }
        return deleteHistories;
    }

    public Answers add(Answer answer){
        List<Answer> newAnswers = new ArrayList<>(answers);
        newAnswers.add(answer);
        return new Answers(newAnswers);
    }
}