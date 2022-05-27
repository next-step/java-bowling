package qna.domain;

import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories of(User loginUser, Question question) {
        return new DeleteHistories(question.addDeleteHistroy(loginUser));
    }

    public List<DeleteHistory> getDeleteHistories() {
        return Collections.unmodifiableList(deleteHistories);
    }
}
