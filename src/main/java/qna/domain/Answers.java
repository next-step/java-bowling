package qna.domain;

import qna.CannotDeleteException;

import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public boolean isAllOwner(User other) {
        for (Answer answer : answers) {
            if (!answer.isOwner(other)) {
                return false;
            }
        }
        return true;
    }

    private List<DeleteHistory> getDeleteHistory() {
        return answers.stream().map(Answer::deleteAndGetHistory).collect(Collectors.toList());
    }

    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        if (!isAllOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
        return getDeleteHistory();
    }
}
