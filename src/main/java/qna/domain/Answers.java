package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers() {
        this(new ArrayList<>());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        this.answers.add(answer);
    }

}
