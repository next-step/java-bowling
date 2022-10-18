package qna.domain;

import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AnswerWrapper {
    private final List<Answer> answers;

    public AnswerWrapper(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }
    
    public List<DeleteHistory> deleteAll(User loginUser) throws CannotDeleteException {
        checkDeletable(loginUser);
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        this.answers.forEach((answer)->{
            answer.setDeleted(true);
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        });
        return deleteHistories;
    }

    private void checkDeletable(User loginUser) throws CannotDeleteException {
        for (Answer answer : this.answers) {
            checkAnswerOwner(loginUser, answer);
        }
    }

    private void checkAnswerOwner(User loginUser, Answer answer) throws CannotDeleteException {
        if (!answer.isOwner(loginUser)) {
            throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
        }
    }
}
