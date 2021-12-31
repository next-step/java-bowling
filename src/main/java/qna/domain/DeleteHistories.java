package qna.domain;

import java.time.LocalDateTime;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories;

    public void add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }
}
