package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteHistories {
    private List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories() {
    }

    public DeleteHistories(List<DeleteHistory> deleteHistories) {
        this.deleteHistories = deleteHistories;
    }

    public void add(long questionId, Question question) {
        question.setDeleted(true);
        LocalDateTime now = LocalDateTime.now();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, questionId, question.getWriter(), now));

        for (Answer answer : question.getAnswers()) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), now));
        }
    }

    public List<DeleteHistory> getHistories() {
        return deleteHistories;
    }
}
