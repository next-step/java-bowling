package step1.qna.domain;

import step1.qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class AnswerList {
    private final List<Answer> answerList;

    public AnswerList(Question question) {
        this.answerList = question.getAnswers();
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answerList) {
            deleteHistories.add(answer.delete(loginUser));
        }

        return deleteHistories;
    }
}
