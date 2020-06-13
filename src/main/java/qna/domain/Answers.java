package qna.domain;

import java.util.Collections;
import java.util.List;

public class Answers {

    private final List<Answer> answers;

    private Answers(final List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public static Answers of(final List<Answer> answers) {
        return new Answers(answers);
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
