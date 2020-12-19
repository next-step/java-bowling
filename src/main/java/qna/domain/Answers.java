package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers = new ArrayList<>();

    public void addAnswer(Answer newAnswer, Question question) {
        newAnswer.toQuestion(question);
        answers.add(newAnswer);
    }

    public void checkAnswersOwner(User loginUser) throws CannotDeleteException {
        for (Answer answer : answers) {
            answer.checkOwner(loginUser);
        }
    }

    public void deleteAnswers(DeleteHistories deleteHistories, User deletedBy) {
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistories.addHistory(ContentType.ANSWER, deletedBy);
        }
    }
}
