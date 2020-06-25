package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            if(answer.deleteByOwner(loginUser)){
                DeleteHistory deleteHistory = new DeleteHistory(ContentType.ANSWER, answer.getId(), loginUser, LocalDateTime.now());
                deleteHistories.add(deleteHistory);
            }
        }
        return deleteHistories;
    }
}
