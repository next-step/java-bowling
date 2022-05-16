package qna.domain;

import qna.service.DeleteHistoryService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }
    public DeleteHistories() {
        this.deleteHistories = new ArrayList();
    }

    public void add(DeleteHistory deleteHistory) {
        this.deleteHistories.add(deleteHistory);
    }

    public void saveAll(DeleteHistoryService deleteHistoryService) {
        deleteHistoryService.saveAll(this.deleteHistories);
    }
}
