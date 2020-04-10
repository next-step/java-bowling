package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    List<DeleteHistory> deleteHistoryLies = new ArrayList<>();

    public List<DeleteHistory> getDeleteHistoryLies() {
        return deleteHistoryLies;
    }

    public void add(DeleteHistory deleteHistory) {
        deleteHistoryLies.add(deleteHistory);
    }

    public void deleteHistory(Question question) {
        DeleteHistory questionDeleteHistory = DeleteHistory.deleteQuestion(question);
        deleteHistoryLies.add(questionDeleteHistory);
    }

    public int size() {
        return deleteHistoryLies.size();
    }
}
