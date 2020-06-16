package qna.domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public boolean containOthers(User user) {
        return answers.stream()
                .anyMatch(answer -> !answer.isOwner(user));
    }

    public List<DeleteHistory> delete() {
        return answers.stream()
                .map(answers -> answers.delete())
                .collect(Collectors.toList());
    }
}
