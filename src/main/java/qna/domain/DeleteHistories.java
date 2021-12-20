package qna.domain;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories(Question question) {
        addQuestionDeleteHistory(question);
        addAnswersDeleteHistories(question.getAnswers());
    }

    private void addQuestionDeleteHistory(Question question) {
        deleteHistories.add(DeleteHistory.from(question));
    }

    private void addAnswersDeleteHistories(Answers answers) {
        answers.get().forEach(answer -> deleteHistories.add(DeleteHistory.from(answer)));
    }

    public List<DeleteHistory> get() {
        return Collections.unmodifiableList(this.deleteHistories);
    }
}
