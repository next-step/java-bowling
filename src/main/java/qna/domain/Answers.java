package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        this.answers.add(answer);
    }

    public void checkPrivilegeOnAnswer(User loginUser) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
        }
    }

    public DeleteHistories deleteHistory(DeleteHistories deleteHistories) {
        for (Answer answer : this.answers) {
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        return deleteHistories;
    }

}
