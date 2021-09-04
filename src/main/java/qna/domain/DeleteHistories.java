package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories;

    private DeleteHistories(final List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories of(final Question question) {
        return new DeleteHistories(Collections.singletonList(DeleteHistory.of(question)));
    }

    public static DeleteHistories of(final Answers answers) {
        return new DeleteHistories(DeleteHistory.of(answers));
    }

    public static DeleteHistories concat(final DeleteHistories deleteHistoriesByQuestion, final DeleteHistories deleteHistoriesByAnswers) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.addAll(deleteHistoriesByQuestion.elements());
        deleteHistories.addAll(deleteHistoriesByAnswers.elements());
        return new DeleteHistories(deleteHistories);
    }

    public List<DeleteHistory> elements() {
        return Collections.unmodifiableList(deleteHistories);
    }
}
