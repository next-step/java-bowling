package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

public class Answers {

    private final List<Answer> answerList;

    private Answers(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public static Answers from(List<Answer> answerList) {
        return new Answers(answerList);
    }

    public int getSize() {
        return answerList.size();
    }

    public void checkOwners(User loginUser) throws CannotDeleteException {
        for (Answer answer : answerList) {
            answer.checkWriter(loginUser);
        }
    }

    public DeleteHistories setDeleteStatus(DeleteHistories deleteHistories) {
        for (Answer answer : answerList) {
            answer.setDeletedTrue();
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        return deleteHistories;
    }
}
