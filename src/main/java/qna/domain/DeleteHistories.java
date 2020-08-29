package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> histories;

    private DeleteHistories() {
        this.histories = new ArrayList<>();
    }

    public static DeleteHistories emptyHistories() {
        return new DeleteHistories();
    }

    public DeleteHistories record(DeleteHistory history) {
        histories.add(history);
        return this;
    }

    public DeleteHistories recordAll(List<DeleteHistory> history) {
        histories.addAll(history);
        return this;
    }

    public List<DeleteHistory> toList() {
        return Collections.unmodifiableList(histories);
    }
}
