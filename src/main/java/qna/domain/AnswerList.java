package qna.domain;

import java.util.List;

public class AnswerList {

    private List<Answer> answers;

    public AnswerList(List<Answer> answers) {
        this.answers = answers;
    }

    public boolean canDelete(User loginUser) {
        boolean canDelete = true;
        for (Answer answer : answers) {
            canDelete &= answer.isOwner(loginUser);
        }
        return canDelete;
    }
}
