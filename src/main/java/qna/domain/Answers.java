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

    public void delete() {
        answers.forEach(Answer::delete);
    }

    public boolean isDeleted(int answerIndex) {
        return this.answers.get(answerIndex).isDeleted();
    }
}
