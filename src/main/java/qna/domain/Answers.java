package qna.domain;

import java.util.Collections;
import java.util.List;

public class Answers {

    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public static Answers of(List<Answer> answers) {
        return new Answers(answers);
    }

    public void deleteAll() {
        answers.forEach(Answer::delete);
    }

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }
}
