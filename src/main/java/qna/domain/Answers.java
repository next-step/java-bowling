package qna.domain;

import org.hibernate.annotations.Where;
import qna.CannotDeleteException;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

public class Answers {
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answers = new ArrayList<>();

    public List<DeleteHistory> deleteAnswer() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter()));
        }
        return deleteHistories;
    }

    public void isOtherAnswer(User loginUser) throws CannotDeleteException {
        checkNullUser(loginUser);
        for (Answer answer : answers) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }
    }

    public void addAnswer(Answer answer) {
        checkNullAnswer(answer);
        answers.add(answer);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    private void checkNullUser(User loginUser) {
        if (loginUser == null) {
            throw new NullPointerException("사용자 정보는 null이 존재할 수 없습니다.");
        }
    }

    private void checkNullAnswer(Answer answer) {
        if (answer == null) {
            throw new NullPointerException("답변정보는 null이 존재할 수 없습니다.");
        }
    }
}
