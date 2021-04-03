package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistoryList {

    private List<DeleteHistory> deleteHistories;

    public DeleteHistoryList() {
        this.deleteHistories = new ArrayList<>();
    }

    public List<DeleteHistory> delete(Question question, AnswerList answerList) {
        this.deleteHistories.add(question.delete());
        this.deleteHistories.addAll(answerList.delete());
        return this.deleteHistories;
    }

}
