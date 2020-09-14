package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories() {
    }

    public DeleteHistories(Question question) {
        deleteHistories.add(DeleteHistory.questionOf(question));
    }

    public void add(Answer answer) {
        deleteHistories.add(DeleteHistory.answerOf(answer));
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
