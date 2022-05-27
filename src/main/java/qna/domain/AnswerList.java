package qna.domain;

import qna.CannotDeleteException;

import java.util.Collections;
import java.util.List;

public class AnswerList {

    private final List<Answer> answerList;

    public AnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public void checkLoginUserEqualWithAnswersOwners(User loginUser) throws CannotDeleteException {
        for (Answer answer : this.answerList) {
            throwIfOwnerAndLoginUserNotEqual(loginUser, answer);
        }
    }

    private void throwIfOwnerAndLoginUserNotEqual(User loginUser, Answer answer) throws CannotDeleteException {
        if (!answer.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public List<Answer> value() {
        return Collections.unmodifiableList(this.answerList);
    }
}
