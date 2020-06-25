package qna.domain;

import qna.CannotDeleteException;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Embeddable
public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public List<DeleteHistory> delete(User loginUser, List<DeleteHistory> deleteHistories) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.deleteByOwner(loginUser);
            DeleteHistory deleteHistory = new DeleteHistory(ContentType.ANSWER, answer.getId(), loginUser, LocalDateTime.now());
            deleteHistories.add(deleteHistory);
        }
        return deleteHistories;
    }
}
