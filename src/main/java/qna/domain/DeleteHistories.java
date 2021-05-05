package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories() {
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public void add(Question question) {
        deleteHistories.add(question.info());
    }

    public void add(Answers answers) {
        answers.addDeleteHistory(this);
    }

    public void add(Answer answer) {
        deleteHistories.add(answer.info());
    }

    public List<DeleteHistory> info() {
        return deleteHistories;
    }

}
