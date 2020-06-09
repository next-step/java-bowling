package qna.domain;

import qna.CannotDeleteException;

import java.util.List;

public class Answers {
    private final List<Answer> answers;

    private Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers of (List<Answer> answers) {
        return new Answers(answers);
    }

    public void validateDelete (User loginUser) throws CannotDeleteException {
        boolean isMatch = answers.stream()
                                 .anyMatch(answer -> !answer.isOwner(loginUser));
        if (isMatch) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}