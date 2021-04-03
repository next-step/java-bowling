package qna.domain;

import qna.CannotDeleteException;

import java.util.List;
import java.util.stream.Collectors;

public class AnswerList {

    private List<Answer> answers;

    public AnswerList(Question question) {
        this.answers = question.getAnswers();
    }

    public boolean canDelete(User loginUser) {
        boolean canDelete = true;
        for (Answer answer : answers) {
            canDelete &= answer.isOwner(loginUser);
        }
        return canDelete;
    }

    public List<DeleteHistory> delete() {
        return this.answers.stream()
                .map(Answer::delete)
                .collect(Collectors.toList());
    }

    public void preCheckDeletion(User loginUser) throws CannotDeleteException {
        if (!canDelete(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
