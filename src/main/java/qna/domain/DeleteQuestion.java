package qna.domain;

import qna.CannotDeleteException;

import java.util.ArrayList;
import java.util.List;

public class DeleteQuestion {
    Question question = new Question();
    Answers answers;


    public DeleteQuestion(Question question) {
        this.question = question;
        answers = new Answers(question);
    }

    public List<DeleteHistory> deleteAfterCheck(User loginUser) throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        checkUser(loginUser);
        delete();
        deleteHistory(deleteHistories);
        return deleteHistories;
    }

    private void deleteHistory(List<DeleteHistory> deleteHistories) {
        question.deleteHistory(deleteHistories);
        answers.deleteHistory(deleteHistories);
    }

    private void delete() {
        answers.delete();
        question.delete();
    }

    private void checkUser(User loginUser) throws CannotDeleteException {
        question.checkUser(loginUser);
        answers.checkUser(loginUser);
    }
}
