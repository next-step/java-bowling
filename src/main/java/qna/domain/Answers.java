package qna.domain;

import qna.CannotDeleteException;

import java.util.Collections;
import java.util.List;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void checkLoginUserEqualWithAnswersOwners(User loginUser) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            throwIfOwnerAndLoginUserNotEqual(loginUser, answer);
        }
    }

    private void throwIfOwnerAndLoginUserNotEqual(User loginUser, Answer answer) throws CannotDeleteException {
        if (!answer.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public List<Answer> value() {
        return Collections.unmodifiableList(this.answers);
    }
}
