package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        deleteHistories = new ArrayList<>();
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public void add(Question question, User loginUser) throws CannotDeleteException {
        question.checkLoginUserEqualWithQuestionOwner(loginUser);
        question.setDeleted(true);
        deleteHistories.add(new DeleteHistory(question));
    }

    public void add(Answers answers, User loginUser) throws CannotDeleteException {
        answers.checkLoginUserEqualWithAnswersOwners(loginUser);
        for (Answer answer : answers.value()) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
    }

    public List<DeleteHistory> value() {
        return Collections.unmodifiableList(deleteHistories);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeleteHistories that = (DeleteHistories) o;

        return deleteHistories != null ? deleteHistories.equals(that.deleteHistories) : that.deleteHistories == null;
    }

    @Override
    public int hashCode() {
        return deleteHistories != null ? deleteHistories.hashCode() : 0;
    }
}
