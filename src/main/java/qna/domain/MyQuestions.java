package qna.domain;

import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.List;

public class MyQuestions {
    private final List<Question> questions;
    private final User loginUser;

    public MyQuestions(List<Question> questions, User loginUser) throws CannotDeleteException {
        ownerValidatation(questions, loginUser);

        this.questions = questions;
        this.loginUser = loginUser;
    }

    private void ownerValidatation(List<Question> questions, User loginUser) throws CannotDeleteException {
        boolean isOnwer = questions.stream()
                .anyMatch(question -> question.isOwner(loginUser));

        if (!isOnwer) {
            throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
        }
    }

    public static MyQuestions of(Question question, User loginUser) throws CannotDeleteException {
        return new MyQuestions(Arrays.asList(question), loginUser);
    }

    public void delete(List<DeleteHistory> deleteHistory) throws CannotDeleteException {
        for (Question question : questions) {
            question.deleted(loginUser, deleteHistory);
        }
    }


}
