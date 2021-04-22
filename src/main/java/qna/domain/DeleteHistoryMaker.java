package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistoryMaker {
    private final QuestionDelete questionDelete;
    private final AnswersDelete answersDelete;

    public DeleteHistoryMaker(QuestionDelete questionDelete, AnswersDelete answersDelete) {
        this.questionDelete = questionDelete;
        this.answersDelete = answersDelete;
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
}
