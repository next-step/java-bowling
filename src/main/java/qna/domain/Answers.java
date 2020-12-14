package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created : 2020-12-14 오전 9:59
 * Developer : Seo
 */
public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public void isOwner(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.isOwner(loginUser);
        }
    }

    public void delete(List<DeleteHistory> deleteHistories) {
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
    }

    public int size() {
        return this.answers.size();
    }
}
