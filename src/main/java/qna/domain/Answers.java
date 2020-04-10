package qna.domain;

import java.util.Collections;
import java.util.List;

import qna.CannotDeleteException;

public class Answers {
    List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = Collections.unmodifiableList(answers);
    }

    public List<Answer> deleteAnswers(User user, DeleteHistories deleteHistories) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.delete(user);
            deleteHistories.addDeleteHistoryForAnswer(answer);
        }
        return answers;
    }
}
