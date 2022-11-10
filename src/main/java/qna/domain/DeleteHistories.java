package qna.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public DeleteHistories(DeleteHistory... deleteHistories) {
        this.deleteHistories = new ArrayList<>(Arrays.asList(deleteHistories));
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

    public void addAll(DeleteHistories deleteHistories) {
        this.deleteHistories.addAll(deleteHistories.getDeleteHistories());
    }


}
