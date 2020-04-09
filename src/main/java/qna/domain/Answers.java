package qna.domain;

import org.springframework.util.CollectionUtils;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void delete(User writer, List<DeleteHistory> deleteHistories) throws CannotDeleteException {
        checkRemovable(writer);
        for (Answer answer : answers) {
            answer.delete();
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
    }

    private void checkRemovable(User writer) throws CannotDeleteException {
        boolean result = answers.stream()
                .anyMatch(answer -> !answer.isOwner(writer));
        if (result) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
