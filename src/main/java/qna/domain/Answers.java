package qna.domain;

import qna.CannotDeleteException;

import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers(List<Answer> answers)  {
        this.answers = answers;
    }

}
