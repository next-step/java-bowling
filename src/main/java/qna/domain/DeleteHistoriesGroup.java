package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistoriesGroup {

    private final List<DeleteHistory> deleteHistories = new ArrayList<>();

    public void addQuestionHistory(Question question, long questionId) {
        DeleteHistory deleteHistory =
                new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now());
        deleteHistories.add(deleteHistory);
    }

    public void addAnswersHistory(Question question) {
        List<Answer> answers = question.getAnswers();
        for (Answer answer : answers) {
            DeleteHistory deleteHistory =
                    new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());
            deleteHistories.add(deleteHistory);
        }
    }

    public List<DeleteHistory> getDeleteHistories() {
        return Collections.unmodifiableList(deleteHistories);
    }
}
