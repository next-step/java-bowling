package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    public static final String CANNOT_MAKE_DELETE_HISTORIES_MESSAGE = "질문이 삭제되지 않아 삭제내역을 만들 수 없습니다.";

    private final List<DeleteHistory> value;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        value = deleteHistories;
    }

    public DeleteHistories(Question question, Answers answers) throws CannotDeleteException {
        if (!question.isDeleted()) {
            throw new CannotDeleteException(CANNOT_MAKE_DELETE_HISTORIES_MESSAGE);
        }

        value = new ArrayList<>();
        value.add(question.makeDeleteHistory());
        value.addAll(answers.makeDeleteHistories());
    }

    public List<DeleteHistory> value() {
        return Collections.unmodifiableList(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeleteHistories that = (DeleteHistories) o;

        return value != null ? value.equals(that.value) : that.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }
}
