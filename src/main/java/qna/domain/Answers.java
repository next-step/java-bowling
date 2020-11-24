package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

class Answers {
    private final List<Answer> answers;

    Answers() {
        answers = new ArrayList<>();
    }

    void add(Answer answer) {
        answers.add(answer);
    }

    void checkDeletable(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.checkDeletable(loginUser);
        }
    }

    List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        return answers
                .stream()
                .map(Answer::delete)
                .collect(toList());
    }

    @Override
    public String toString() {
        return answers.toString();
    }
}
