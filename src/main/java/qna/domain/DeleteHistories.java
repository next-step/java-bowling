package qna.domain;

import java.util.*;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories(Question question) {
        addQuestionDeleteHistory(question);
        addAnswersDeleteHistories(question.getAnswers());
    }

    private void addQuestionDeleteHistory(Question question) {
        deleteHistories.add(DeleteHistory.of(question));
    }

    private void addAnswersDeleteHistories(Answers answers) {
        answers.get().forEach(answer -> deleteHistories.add(DeleteHistory.of(answer)));
    }

    public List<DeleteHistory> get() {
        return Collections.unmodifiableList(this.deleteHistories);
    }
}
