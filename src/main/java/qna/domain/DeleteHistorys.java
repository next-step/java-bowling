package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistorys {
    private final List<DeleteHistory> deleteHistory = new ArrayList<>();

    public DeleteHistorys(Question question) {
        add(question);
    }

    private void add(Question question) {
        deleteHistory.add(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
        add(question.answers());
    }

    private void add(Answers answers) {
        answers.answers()
                .forEach(answer -> deleteHistory.add(
                        new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now())));
    }

    public List<DeleteHistory> histories() {
        return Collections.unmodifiableList(deleteHistory);
    }
}
