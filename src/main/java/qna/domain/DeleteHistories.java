package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        deleteHistories = new ArrayList<>();
    }

    private DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public DeleteHistories add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
        return new DeleteHistories(deleteHistories);
    }

    public int getSize() {
        return deleteHistories.size();
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
