package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories;

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories of(DeleteHistory ...deleteHistory) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        Collections.addAll(deleteHistories, deleteHistory);
        return new DeleteHistories(deleteHistories);
    }

    public DeleteHistories addAll(List<DeleteHistory> targetDeleteHistories) {
        deleteHistories.addAll(targetDeleteHistories);

        return this;
    }

    public List<DeleteHistory> toList() {
        return deleteHistories;
    }
}
