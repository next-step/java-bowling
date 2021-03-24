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
public class AnswerList {

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    @Where(clause = "deleted = false")
    @OrderBy("id ASC")
    private List<Answer> answerList = new ArrayList<>();

    public AnswerList() {
    }

    public AnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void add(Answer answer) {
        answerList.add(answer);
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void deleteAllBy(User loginUser) throws CannotDeleteException {
        validateAllAuthority(loginUser);
        for (Answer answer : answerList) {
            answer.setDeleted(true);
        }
    }

    private void validateAllAuthority(User loginUser) throws CannotDeleteException {
        for (Answer answer : answerList) {
            validateSingleAuthority(answer, loginUser);
        }
    }

    private void validateSingleAuthority(Answer answer, User loginUser) throws CannotDeleteException {
        if (!answer.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

}
