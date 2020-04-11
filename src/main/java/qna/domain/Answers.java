package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : this.answers) {
            if (!answer.isOwner(loginUser)) {
                throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
            }
            Answer deletedAnswer = answer.delete();
            deleteHistories.add(new DeleteHistory(
                    ContentType.ANSWER, deletedAnswer.getId(), deletedAnswer.getWriter(), LocalDateTime.now()));
        }
        return deleteHistories;
    }
}
