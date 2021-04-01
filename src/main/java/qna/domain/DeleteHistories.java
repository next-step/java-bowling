package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories of(User loginUser, Question question) {
        List<DeleteHistory> result = new ArrayList<>();
        result.add(question.questionToDeleteHistory(loginUser));
        result.addAll(question.answersToDeleteHistories(loginUser));

        return new DeleteHistories(result);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return Collections.unmodifiableList(deleteHistories);
    }
}
