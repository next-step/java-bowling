package qna.domain;

import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories;

    private DeleteHistories(List deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories create(List deleteHistories) {
        return new DeleteHistories(deleteHistories);
    }

    public void addHistory(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
