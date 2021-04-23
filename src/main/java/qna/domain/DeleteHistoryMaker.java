package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistoryMaker {
    private final User loginUser;
    private final Question question;

    public DeleteHistoryMaker(User loginUser, Question question) {
        this.loginUser = loginUser;
        this.question = question;
    }

    public List<DeleteHistory> make(Answers answers) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(makeDeleteQuestion());
        deleteHistories.addAll(makeDeleteAnswers(answers));
        return deleteHistories;
    }

    private DeleteHistory makeDeleteQuestion() throws CannotDeleteException {
        return question.delete(loginUser);
    }

    private List<DeleteHistory> makeDeleteAnswers(Answers answers) throws CannotDeleteException {
        return answers.delete(loginUser);
    }
}
