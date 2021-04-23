package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistoryMaker {
    private QuestionDelete questionDelete;
    private AnswersDelete answersDelete;

    private User loginUser;
    private Question question;

    public DeleteHistoryMaker(QuestionDelete questionDelete, AnswersDelete answersDelete) {
        this.questionDelete = questionDelete;
        this.answersDelete = answersDelete;
    }

    public DeleteHistoryMaker(User loginUser, Question question) {
        this.loginUser = loginUser;
        this.question = question;
    }

    public List<DeleteHistory> make(User user) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(makeDeleteQuestion(user));
        deleteHistories.addAll(makeDeleteAnswers(user));
        return deleteHistories;
    }

    private DeleteHistory makeDeleteQuestion(User user) throws CannotDeleteException {
        return questionDelete.toDeleteHistory(user);
    }

    private List<DeleteHistory> makeDeleteAnswers(User user) throws CannotDeleteException {
        return answersDelete.toDeleteHistories(user);
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
