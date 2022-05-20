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

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    private List<DeleteHistory> changeAnswersDeleteState(User loginUser) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.forEach(answer -> {
            try {
                deleteHistories.add(answer.delete(loginUser));
            } catch (CannotDeleteException e) {
                e.printStackTrace();
                throw new RuntimeException(loginUser + "님 의 삭제가 거부되었습니다. 전체 비지니스 로직을 종료합니다.");
            }
        });
        return deleteHistories;
    }

    public List<DeleteHistory> answersDelete(User loginUser) {
        canDeletedAnswerCondition(loginUser);
        return changeAnswersDeleteState(loginUser);
    }

    public boolean canDeletedAnswerCondition(User loginUser) {
        return answers.stream().anyMatch(answer -> !answer.isOwner(loginUser));
    }
}
