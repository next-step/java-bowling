package qna.domain;

import qna.CannotDeleteException;

import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers(Question question) {
        this(question.getAnswers());
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void deletable(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.deletable(loginUser);
        }
    }

    public List<Answer> getAnswers() {
        return answers;
    }
}
