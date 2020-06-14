package qna.domain;

import qna.CannotDeleteException;

import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void validateOwner(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            isAnswerOwner(answer, loginUser);
        }
    }

    private void isAnswerOwner(Answer answer, User loginUser) throws CannotDeleteException {
        if (!answer.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public List<DeleteHistory> createDeleteHistories() {
        return this.answers.stream()
                .map(Answer::createDeleteHistory)
                .collect(Collectors.toList());
    }
}
