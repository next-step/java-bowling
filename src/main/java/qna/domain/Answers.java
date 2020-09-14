package qna.domain;

import java.util.Collections;
import java.util.List;

import qna.CannotDeleteException;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public void delete(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(loginUser);
        }
    }

    public List<Answer> getCollection() {
        return answers;
    }
}
