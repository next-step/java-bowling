package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this(new ArrayList<>());
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public void insert(Question question, Answers answers) {
        question.delete();
        deleteHistories.add(new DeleteHistory(question));
        for (Answer answer : answers.getAnswers()) {
            answer.delete();
            deleteHistories.add(new DeleteHistory(answer));
        }
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }
}
