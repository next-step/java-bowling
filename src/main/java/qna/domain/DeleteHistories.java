package qna.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeleteHistories that = (DeleteHistories) o;
        return Objects.equals(this.deleteHistories, that.deleteHistories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.deleteHistories);
    }
}
