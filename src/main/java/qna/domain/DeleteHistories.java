package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> value;

    public DeleteHistories() {
        value = new ArrayList<>();
    }

    public void add(DeleteHistory deleteHistory) {
        value.add(deleteHistory);
    }

    public void addAll(List<DeleteHistory> deleteHistories) {
        value.addAll(deleteHistories);
    }

    public List<DeleteHistory> getValue() {
        return value;
    }
}
