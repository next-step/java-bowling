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

    public List<DeleteHistory> deleteAnswers(long questionId, User deletedBy) {
        List<DeleteHistory> deleteHistoriesList = new ArrayList<>();
        for (Answer answer : answers) {
            answer.setDeleted(true);
            deleteHistoriesList.add(answer.createDeleteHistory(questionId, deletedBy));
        }

        return deleteHistoriesList;
    }
}
