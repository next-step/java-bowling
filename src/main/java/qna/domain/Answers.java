package qna.domain;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers(List<Answer> answerList) {
        this.answers = new ArrayList<>(answerList);
    }

    public int size() {
        return this.answers.size();
    }

    public void delete(User questionUser) {
        answers.forEach(answer -> answer.delete(questionUser));
    }
}
