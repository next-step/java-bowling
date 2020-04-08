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

    public void checkRemovable(User writer) throws CannotDeleteException {
        boolean result = answers.stream()
                .allMatch(answer -> answer.isOwner(writer));
        if (!result) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public DeleteHistories delete(DeleteHistories deleteHistories) {
        for (Answer answer : answers) {
            answer.delete();
            deleteHistories = deleteHistories.addAnswer(answer);
        }
        return deleteHistories;
    }
}
