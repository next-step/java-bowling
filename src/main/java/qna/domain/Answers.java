package qna.domain;

import qna.CannotDeleteException;

import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<DeleteHistory> deleteAllAnswers(User loginUser) throws CannotDeleteException {
        validateExistMessage(loginUser);
        return answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    private void validateExistMessage(User loginUser) throws CannotDeleteException {
        if(checkOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    private boolean checkOwner(User loginUser) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(loginUser));
    }

}
