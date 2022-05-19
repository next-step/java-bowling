package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories = new ArrayList<>();

    public int size() {
        return deleteHistories.size();
    }

    public void add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public List<DeleteHistory> deleteHistories() {
        return deleteHistories;
    }
}
