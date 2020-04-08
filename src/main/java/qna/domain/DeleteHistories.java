package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(final List<DeleteHistory> deleteHistories) {
        this.deleteHistories = Collections.unmodifiableList(deleteHistories);
    }

    public DeleteHistories merge(final DeleteHistories other) {
        List<DeleteHistory> source = new ArrayList<>();
        source.addAll(deleteHistories);
        source.addAll(other.deleteHistories);
        return new DeleteHistories(source);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
