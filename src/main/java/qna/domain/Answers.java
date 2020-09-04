package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public void delete() {
//        for (Answer answer : answers) {
//            answer.setDeleted(true);
//            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
//        }
    }

    public void validateOwner(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateOwner(loginUser);
        }
    }
}
