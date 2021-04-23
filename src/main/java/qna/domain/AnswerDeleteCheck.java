package qna.domain;

import qna.CannotDeleteException;

public class AnswerDeleteCheck {
    private final Answer answer;

    private AnswerDeleteCheck(Answer answer) {
        this.answer = answer;
    }

    public static AnswerDeleteCheck of(Answer answer) {
        return new AnswerDeleteCheck(answer);
    }

    public boolean authCheck(User loginUser) throws CannotDeleteException {
        if (!answer.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        return true;
    }
}
