package qna.domain;

import java.util.Collections;
import java.util.List;

import qna.CannotDeleteException;

public class Answers {
    List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public void checkDeleteAnswers(User user) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.validateAnswer(user);
        }
    }

    public List<Answer> deleteAnswers(DeleteHistories deleteHistories) {
        for (Answer answer : answers) {
            answer.delete();
            deleteHistories.addDeleteHistoryForAnswer(answer);
        }
        return answers;
    }
}
