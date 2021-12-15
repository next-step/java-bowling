package qna.domain;

import qna.CannotDeleteException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {
    private final List<Answer> value;

    public Answers(List<Answer> answers) {
        this.value = answers;
    }

    public static Answers from(List<Answer> answers) {
        return new Answers(answers);
    }

    public void checkOwnerForDelete(User loginUser) throws CannotDeleteException {
        for (Answer answer : value) {
            answer.checkOwnerForDelete(loginUser);
        }
    }

    public void deleteAll() {
        value.forEach(Answer::delete);
    }

    public List<DeleteHistory> makeDeleteHistories() {
        return value.stream()
                .map(Answer::makeDeleteHistory)
                .collect(Collectors.toList());
    }

    public List<Answer> value() {
        return Collections.unmodifiableList(value);
    }
}
