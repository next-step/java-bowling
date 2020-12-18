package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

public class Answers {

    private final List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public int getSize() {
        return answers.size();
    }

    public void checkOwners(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.checkWriter(loginUser);
        }
    }

    public DeleteHistories setDeleteStatus(DeleteHistories deleteHistories) {
        for (Answer answer : answers) {
            answer.setDeletedTrue();
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        return deleteHistories;
    }
}
