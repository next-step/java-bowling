package qna.domain;

import qna.CannotDeleteException;

public class QuestionDelete {
    private final Question question;

    public QuestionDelete(Question question) {
        this.question = question;
    }

    public DeleteHistory toDeleteHistory(User user) throws CannotDeleteException {
        question.checkWriter(user);
        return question.toDeleteHistory();
    }
}
