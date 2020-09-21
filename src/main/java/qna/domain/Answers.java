package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers;

    private Answers(List<Answer> answer) {
        this.answers = answer;
    }

    public static Answers of(List<Answer> answer) {
        return new Answers(answer);
    }

    public List<DeleteHistory> delete(User loginUser, LocalDateTime now) throws CannotDeleteException {
        validateDeletion(loginUser);
        return answers.stream()
                .peek(it -> it.setDeleted(true))
                .map(it -> new DeleteHistory(ContentType.ANSWER, it.getId(), it.getWriter(), now))
                .collect(Collectors.toList())
                ;

    }

    private void validateDeletion(User loginUser) throws CannotDeleteException {
        if (answers.stream()
                .anyMatch(it -> !it.isOwner(loginUser))
        ) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
