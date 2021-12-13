package qna.domain;

import java.util.List;
import java.util.stream.Collectors;

import qna.CannotDeleteException;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        validateOwnerOrThrow(loginUser);
        return answers.stream()
                      .peek(Answer::delete)
                      .map(answer -> new DeleteHistory(answer, loginUser))
                      .collect(Collectors.toList());

    }

    private void validateOwnerOrThrow(User loginUser) throws CannotDeleteException {
        boolean notOwnerExist = answers.stream()
                                       .anyMatch(answer -> !answer.isOwner(loginUser));
        if (notOwnerExist) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
