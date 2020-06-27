package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories;

    private DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public static DeleteHistories of() {
        return new DeleteHistories();
    }

    public void addToDeleteHistories(Question question) {
        DeleteHistory questionHistory = question.generateDeleteHistoryForQuestion();
        this.deleteHistories.add(questionHistory);

        addToDeleteHistoriesForAnswers(question.getAnswers());
    }

    public List<DeleteHistory> getDeleteHistories() {
        return Collections.unmodifiableList(this.deleteHistories);
    }

    private void addToDeleteHistoriesForAnswers(Answers answers) {
        List<DeleteHistory> deleteHistories = answers.generateDeleteHistories();
        this.deleteHistories.addAll(deleteHistories);
    }
}
