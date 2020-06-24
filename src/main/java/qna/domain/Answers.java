package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> answers;

    public Answers(final List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> deleteAll(User loginUser) throws CannotDeleteException {
        validateAnswersOwner(loginUser);

        return answers.stream()
                .map(Answer::delete)
                .map(deleted -> new DeleteHistory(ContentType.ANSWER, deleted.getId(), loginUser, LocalDateTime.now()))
                .collect(Collectors.toList());
    }

    private void validateAnswersOwner(User loginUser) throws CannotDeleteException {
        Optional<Answer> findAnswer = answers.stream()
                .filter(answer -> !answer.isOwner(loginUser))
                .findAny();

        if (findAnswer.isPresent()) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 존재해서 삭제할 수 없습니다.");
        }
    }
}
