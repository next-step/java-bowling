package qna.domain;

import qna.CannotDeleteException;

import java.util.List;

public class AnswersDelete extends Answer {
    private final Answers answers;

    public AnswersDelete(Answers answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> toDeleteHistories(User user) throws CannotDeleteException {
        answers.checkWriter(user);
        return answers.toDeleteHistories();
    }
}
