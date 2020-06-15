package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers(List<Answer> answerList) {
        this.answers = new ArrayList<>(answerList);
    }
}
