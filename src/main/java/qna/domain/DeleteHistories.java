package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories() {
        this.deleteHistories = new ArrayList<>();
    }

    public static DeleteHistories build() {
        return new DeleteHistories();
    }

    public DeleteHistories addQuestionHistory(Question question, LocalDateTime now) {
        deleteHistories.add(DeleteHistory.ofQuestion(question, now));
        return this;
    }

    public DeleteHistories addAnswersHistories(Answers answers, LocalDateTime now) {
        answers.getCollection()
               .forEach(answer -> deleteHistories.add(DeleteHistory.ofAnswer(answer, now)));
        return this;
    }

    public List<DeleteHistory> getCollection() {
        return Collections.unmodifiableList(deleteHistories);
    }
}
