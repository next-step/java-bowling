package qna.domain;

import java.util.Collections;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void delete(User loginUser) {
        answers.stream()
                .forEach(answer -> answer.delete(loginUser));
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }
}