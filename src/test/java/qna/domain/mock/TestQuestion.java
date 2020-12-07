package qna.domain.mock;

import qna.CannotDeleteException;
import qna.domain.DeleteHistories;
import qna.domain.Question;
import qna.domain.User;

public class TestQuestion extends Question {
    private boolean deleted;

    public TestQuestion(Question question) {
        super(question);
        deleted = false;
    }

    public TestQuestion(String title, String contents) {
        super(title, contents);
        deleted = false;
    }

    @Override
    public DeleteHistories delete(User loginUser) throws CannotDeleteException {
        deleted = true;
        return super.delete(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }
}
