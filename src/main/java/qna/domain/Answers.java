package qna.domain;

import qna.CannotDeleteException;

import java.util.List;

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
}
