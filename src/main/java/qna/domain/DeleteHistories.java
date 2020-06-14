package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        this.deleteHistories.add(question.createDeleteHistory());
    }

    public void addAnswers(Answers answers) {
        this.deleteHistories.addAll(answers.createDeleteHistories());
    }

    public int count() {
        return this.deleteHistories.size();
    }

    public List<DeleteHistory> toList() {
        return Collections.unmodifiableList(this.deleteHistories);
    }
}
