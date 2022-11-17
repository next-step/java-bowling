package step1.qna.domain;

import step1.qna.CannotDeleteException;

import java.util.List;

public class AnswerList {
    private final List<Answer> answerList;

    public AnswerList(Question question) {
        this.answerList = question.getAnswers();
    }

    public void checkDeleteCondition(User loginUser) throws CannotDeleteException {
        for (Answer answer : answerList) {
            answer.checkDeleteCondition(loginUser);
        }
    }

    public void deleteAndRecord(List<DeleteHistory> deleteHistories) {
        for (Answer answer : answerList) {
            answer.deleteAndRecord(deleteHistories);
        }
    }
}
