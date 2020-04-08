package qna.domain;

import java.util.Collections;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void delete(User loginUser) {

    }
}
