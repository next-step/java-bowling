package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> histories = new ArrayList<>();

    public void add(DeleteHistory deleteHistory) {
        histories.add(deleteHistory);
    }

    public List<DeleteHistory> getHistories() {
        return Collections.unmodifiableList(histories);
    }
}
