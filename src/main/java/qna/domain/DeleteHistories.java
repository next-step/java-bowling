package qna.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteHistories implements Iterable<DeleteHistory> {
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

    @Override
    public Iterator<DeleteHistory> iterator() {
        return value.iterator();
    }
}
