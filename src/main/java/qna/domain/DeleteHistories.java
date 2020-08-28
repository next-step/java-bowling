package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> histories = new ArrayList<>();

    public DeleteHistories record(DeleteHistory history) {
        histories.add(history);
        return this;
    }

    public DeleteHistories record(List<DeleteHistory> histories) {
        histories.addAll(histories);
        return this;
    }

    public List<DeleteHistory> toList() {
        return Collections.unmodifiableList(histories);
    }
}
