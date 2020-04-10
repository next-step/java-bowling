package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> histories;

    private DeleteHistories(DeleteHistory history) {
        this.histories = new ArrayList<>();
        this.histories.add(history);
    }

    private DeleteHistories(List<DeleteHistory> histories) {
        this.histories = histories;
    }

    public static DeleteHistories of(List<DeleteHistory> deleteHistories) {
        return new DeleteHistories(deleteHistories);
    }

    public static DeleteHistories of(DeleteHistory deleteHistory) {
        return new DeleteHistories(deleteHistory);
    }

    public void add(DeleteHistories deleteHistories) {
        this.histories.addAll(deleteHistories.histories);
    }

    public List<DeleteHistory> getHistories() {
        return histories;
    }
}
