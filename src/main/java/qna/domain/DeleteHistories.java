package qna.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories(DeleteHistory... deleteHistories) {
        this.deleteHistories.addAll(Arrays.asList(deleteHistories));
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories.addAll(deleteHistories);
    }

    public void add(DeleteHistory history) {
        deleteHistories.add(history);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

    public void addAll(DeleteHistories target) {
        deleteHistories.addAll(target.deleteHistories);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DeleteHistories)) {
            return false;
        }

        DeleteHistories target = (DeleteHistories) obj;
        return deleteHistories.equals(target.deleteHistories);
    }
}
