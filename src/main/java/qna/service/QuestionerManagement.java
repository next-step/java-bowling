package qna.service;

import qna.CannotDeleteException;
import qna.domain.DeleteHistory;
import qna.domain.Question;
import qna.domain.User;

import java.util.List;

public class QuestionerManagement {

    private Questioners questioners;

    public QuestionerManagement(List<Question> questions) {

        this.questioners = Questioners.of(questions);
    }

    public static QuestionerManagement of(List<Question> questions) {
        return new QuestionerManagement(questions);
    }

    public List<DeleteHistory> deleteHistory(User userId, long questionId) throws CannotDeleteException {
        return questioners.delete(userId, questionId);
    }

}
