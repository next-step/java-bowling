package qna.domain;

import qna.CannotDeleteException;

public class QuestionDeleteCheck {
    private final Question question;

    private QuestionDeleteCheck(Question question) {
        this.question = question;
    }

    public static QuestionDeleteCheck of(Question question) {
        return new QuestionDeleteCheck(question);
    }

    public boolean authCheck(User loginUser) throws CannotDeleteException {
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        return true;
    }
}
