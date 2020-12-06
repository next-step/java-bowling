package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static util.Preconditions.checkArgument;

public class Answers {
    public static final String ANSWER_LIST_MUST_NOT_NULL = "answerList 는 null 일 수 없습니다.";
    public static final String EXIST_ANOTHER_USER_ANSWER = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";

    private final List<Answer> answers;

    private Answers(final List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers valueOf(final List<Answer> answerList) {
        checkArgument(Objects.nonNull(answerList), ANSWER_LIST_MUST_NOT_NULL);
        return new Answers(answerList);
    }

    public void delete(final User user, final DeleteHistories deleteHistories) throws CannotDeleteException {
        if (existAnswerWriteByAnother(user)) {
            throw new CannotDeleteException(EXIST_ANOTHER_USER_ANSWER);
        }

        answers.forEach(answer -> {
            answer.setDeleted(true);
            deleteHistories.save(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        });
    }

    private boolean existAnswerWriteByAnother(final User user) {
        return answers.stream()
                .anyMatch(answer -> answer.isNotOwner(user));
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
