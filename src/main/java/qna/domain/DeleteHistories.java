package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public DeleteHistories() {
        this(new ArrayList<>());
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

    public void addAll(List<DeleteHistory> deleteHistories) {
        this.deleteHistories.addAll(deleteHistories);
    }

    public void addQuestionDeleteHistory(Question question) {
        deleteHistories.add(question.generateDeleteHistory());
    }

    public void addAnswersDeleteHistory(Answers answers) {
        addAll(answers.generateDeleteHistories());
    }
}
