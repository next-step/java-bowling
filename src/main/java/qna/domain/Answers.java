package qna.domain;

import qna.CannotDeleteException;

import java.util.List;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void validateDeleteCondition(User loginUser) throws CannotDeleteException {
        boolean isAllWrittenByLoginUser = answers.stream()
                .allMatch(answer -> answer.isOwner(loginUser));
        if (!isAllWrittenByLoginUser) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }

    public void delete() {
        answers.forEach(Answer::delete);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
