package qna.domain;

import qna.ForbiddenException;

import java.util.ArrayList;
import java.util.List;

public class Answers {

    private List<Answer> answers;

    private boolean deleted = false;


    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    private Answers setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public List<DeleteHistory> delete(User loginUser) {

        if (answers.stream().anyMatch(answer -> !answer.isOwner(loginUser))) {
            throw new ForbiddenException("본인이 작성하지 않은 답변이 있어서, 삭제할 수 없습니다");
        }

        answers.forEach(answer -> answer.setDeleted(true));
        setDeleted(true);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.forEach(answer -> deleteHistories.add(new DeleteHistory(answer)));
        return deleteHistories;
    }
}
