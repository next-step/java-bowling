package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories() {
    }

    public void addDeleteHistoryByQuestion(Question question) {
        deleteHistories.add(DeleteHistory.ofQuestion(question));
    }

    public void addDeleteHistoryByAnswer(Answer answer) {
        deleteHistories.add(DeleteHistory.ofAnswer(answer));
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
