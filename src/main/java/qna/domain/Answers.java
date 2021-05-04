package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public Answers() {}

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    private void checkIsEmptyAnswer() throws CannotDeleteException {
        if (answers.size() <= 0) {
            throw new CannotDeleteException("답변이 없습니다.");
        }
    }

    public void isHasAnswersIsNotOwner(User loginUser) throws CannotDeleteException {
        checkIsEmptyAnswer();

        for (Answer answer : answers) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }
    }

    public void deleteAll(DeleteHistories deleteHistories) {
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter()));
        }
    }
}
