package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class DeleteQuestion {

    private final Question question;

    public DeleteQuestion(Question question, User loginUser) throws CannotDeleteException {
        if (!question.isOwner(loginUser)) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
        if (!question.canDeleteAnswer(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        ;
        this.question = question;
    }

    public void delete() {
        question.delete();
        this.deleteAnswers();
    }

    void deleteAnswers() {
        question.deleteAnswers();
    }

    public List<DeleteHistory> deleteHistory() {
        List<DeleteHistory> histories = new ArrayList<>();
        histories.add(DeleteHistory.withQuestion(this.question));
        histories.addAll(this.question.answerHistory());
        return histories;
    }
}
