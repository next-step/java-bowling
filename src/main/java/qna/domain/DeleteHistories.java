package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        deleteHistories = new ArrayList<>();
    }

    public void add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public List<DeleteHistory> values() {
        return deleteHistories;
    }

    public void addAll(DeleteHistories deleteAnswers) {
        deleteHistories.addAll(deleteAnswers.values());
    }
}
