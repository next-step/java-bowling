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

    public Answers() {
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public List<Answer> searchAnswers(User loginUser) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            validateOwner(answer, loginUser);
        }

        return this.answers;
    }

    private void validateOwner(Answer answer, User loginUser) throws CannotDeleteException {
        if (!answer.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public DeleteHistories deleteAnswer(User loginUser, DeleteHistories deleteHistories) throws CannotDeleteException {
        List<Answer> answers = searchAnswers(loginUser);

        answers.stream().forEach((answer) -> deleteAnswer(deleteHistories, answer));

        return deleteHistories;
    }

    private void deleteAnswer(DeleteHistories deleteHistories, Answer answer) {
        answer.deleteState();
        deleteHistories.addAnswerDeleteHistory(answer);
    }
}
