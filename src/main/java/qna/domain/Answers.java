package qna.domain;

import qna.CannotDeleteException;

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

    public void checkOwners(User loginUser) throws CannotDeleteException {
        for (Answer answer : answerList) {
            answer.checkWriter(loginUser);
        }
    }
}
