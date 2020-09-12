package qna.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import qna.CannotDeleteException;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public void addDeleteQuestionHistory(Question question, User loginUser) throws CannotDeleteException {
        deleteHistories.add(question.delete(loginUser));
    }

    public void addDeleteAnswersHistories(Answers answers, User loginUser) throws CannotDeleteException {
        deleteHistories.addAll(answers.delete(loginUser));
    }

    public Collection<DeleteHistory> getCollection() {
        return Collections.unmodifiableList(deleteHistories);
    }
}
