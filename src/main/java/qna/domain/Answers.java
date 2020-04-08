package qna.domain;

import java.util.Collections;
import java.util.List;

public class Answers {
    private final List<Answer> answers;

    public Answers(final List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
