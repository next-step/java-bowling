package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private static final String CANNOT_DELETE_MESSAGE = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    private final List<Answer> answers;

    public Answers(final List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public DeleteHistories delete() {
        for (Answer answer : answers) {
            answer.delete();
        }
        return answers.stream()
                      .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
                      .collect(Collectors.collectingAndThen(Collectors.toList(), DeleteHistories::new));
    }

    public void checkDeletable(final User user) {
        if (hasSomethingElse(user)) {
            throw new CannotDeleteException(CANNOT_DELETE_MESSAGE);
        }
    }

    private boolean hasSomethingElse(final User user) {
        return answers.stream()
                      .anyMatch(answer -> !answer.isOwner(user));
    }
}
