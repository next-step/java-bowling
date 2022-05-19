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
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer answer){
        answers.add(answer);
    }

    public void changeAnswersDeleteState(User loginUser, List<DeleteHistory> deleteHistories){
        answers.stream().forEach(answer -> {
            try {
                 deleteHistories.add(answer.delete(loginUser));
            } catch (CannotDeleteException e) {
                e.printStackTrace();
            }
        });
    }


    public List<DeleteHistory> answersDelete(User loginUser, List<DeleteHistory> deleteHistories){
        canDeletedAnswerCondition(loginUser);
        changeAnswersDeleteState(loginUser, deleteHistories);

        return deleteHistories;
    }

    public boolean canDeletedAnswerCondition(User loginUser) {
        return answers.stream().anyMatch(answer-> !answer.isOwner(loginUser));
    }
}
