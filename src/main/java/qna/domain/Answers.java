package qna.domain;

import java.util.Collections;
import java.util.List;


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

    public void delete() {
        answers.forEach(answer -> answer.delete());
    }
}
