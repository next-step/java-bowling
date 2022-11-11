package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    List<DeleteHistory> values = new ArrayList<>();

    public void add(DeleteHistory deleteHistory) {
        values.add(deleteHistory);
    }

    public void add(List<DeleteHistory> deleteHistories) {
        values.addAll(deleteHistories);
    }

    public List<DeleteHistory> getValues() {
        return Collections.unmodifiableList(values);
    }
}
