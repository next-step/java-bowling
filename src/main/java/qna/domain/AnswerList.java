package qna.domain;

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
}
