package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> value;

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        value = deleteHistories;
    }

    public static DeleteHistories of(DeleteHistory questionDeleteHistory, List<DeleteHistory> answersDeleteHistories) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(questionDeleteHistory);
        deleteHistories.addAll(answersDeleteHistories);
        return new DeleteHistories(deleteHistories);
    }

    public List<DeleteHistory> getValue() {
        return value;
    }
}
