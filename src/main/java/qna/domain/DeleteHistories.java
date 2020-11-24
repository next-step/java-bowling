package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public static DeleteHistories of(DeleteHistory history, List<DeleteHistory> histories) {
        List<DeleteHistory> list = new ArrayList<>();
        list.add(history);
        list.addAll(histories);

        return new DeleteHistories(list);
    }

    public List<DeleteHistory> getAll() {
        return deleteHistories;
    }
}
