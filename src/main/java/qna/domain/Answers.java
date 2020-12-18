package qna.domain;

import java.util.List;

public class Answers {

    private final List<Answer> answerList;

    public Answers(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public static Answers from(List<Answer> answerList) {
        return new Answers(answerList);
    }

    public int getSize() {
        return answerList.size();
    }
}
