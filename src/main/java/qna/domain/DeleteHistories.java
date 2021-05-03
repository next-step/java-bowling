package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories() {
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public void add(DeleteHistory deleteHistory) {
        deleteHistories.add(deleteHistory);
    }

    public void add(Question question) {
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter()));
    }

    public void add(Answers answers) {
        answers.deleteAll(this);
    }
    public DeleteHistoryRepository addAll(DeleteHistoryRepository deleteHistoryRepository) {
        deleteHistoryRepository.saveAll(deleteHistories);
        return deleteHistoryRepository;
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
