package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories =new ArrayList<>();

    public DeleteHistories(DeleteHistory questionDeleteHistory, List<DeleteHistory> answerDeleteHistory) {
        addQuestion(questionDeleteHistory);
        addAnswers(answerDeleteHistory);
    }

    private void addQuestion(DeleteHistory questionDeleteHistory) {
        this.deleteHistories.add(questionDeleteHistory);
    }

    private void addAnswers(List<DeleteHistory> deleteHistories) {
        this.deleteHistories.addAll(deleteHistories);
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

}
