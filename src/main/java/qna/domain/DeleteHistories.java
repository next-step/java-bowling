package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    List<DeleteHistory> deleteHistories = new ArrayList<>();

    public void addDeleteHistoryForQuestion(long questionId, Question question) {
        deleteHistories.add(
                new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), LocalDateTime.now()));
    }

    public void addDeleteHistoryForAnswer(Answer answer) {
        deleteHistories.add(
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
    }

    public List<DeleteHistory> getDeleteHistories() {
        return deleteHistories;
    }

}
