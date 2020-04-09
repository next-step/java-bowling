package qna.domain;

import java.util.List;

import qna.CannotDeleteException;

public class Answers {
    List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void checkDeleteAnswers(User user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateAnswer(user);
        }
    }

    public List<Answer> deleteAnswers() {
        for (Answer answer : answers) {
            answer.deleteAnswer();
        }
        return answers;
    }
}
