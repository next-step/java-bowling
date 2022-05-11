package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.*;

public class DeleteHistories {

    private final List<DeleteHistory> deleteHistories = new ArrayList<>();

    public DeleteHistories(Question question, Answers answers) {
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));

        answers.getAnswers()
                .forEach(answer -> {
                    answer.changeStatusToDeleted();
                    deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
                });
    }

    public List<DeleteHistory> getDeleteHistories() {
        return unmodifiableList(deleteHistories);
    }
}
