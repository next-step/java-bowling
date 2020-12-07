package qna.domain;

import qna.CannotDeleteException;

import java.util.List;
import java.util.Objects;

import static util.Preconditions.checkArgument;

public class Answers {
    public static final String ANSWER_LIST_MUST_NOT_NULL = "answerList 는 null 일 수 없습니다.";

    private final List<Answer> answers;

    private Answers(final List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers valueOf(final List<Answer> answerList) {
        checkArgument(Objects.nonNull(answerList), ANSWER_LIST_MUST_NOT_NULL);
        return new Answers(answerList);
    }

    public void delete(final User user, final DeleteHistories deleteHistories) throws CannotDeleteException {
        if (isEmpty()) {
            return;
        }

        for (Answer answer : answers) {
            answer.delete(user, deleteHistories);
        }
    }

    public boolean isEmpty() {
        return answers.isEmpty();
    }
}
