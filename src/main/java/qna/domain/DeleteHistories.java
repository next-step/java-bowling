package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories;

    public DeleteHistories(Question question, Answers answers) {

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        question.setDeleted(true);
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));

        for (Answer answer : answers.getCollection()) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }

        this.deleteHistories = Collections.unmodifiableList(deleteHistories);
    }

    public Collection<DeleteHistory> getCollection() {
        return deleteHistories;
    }
}
