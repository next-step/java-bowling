package qna.domain.mock;

import qna.CannotDeleteException;
import qna.domain.DeleteHistory;
import qna.domain.Question;
import qna.domain.User;

import java.util.List;

public class TestQuestion extends Question {
    private boolean deleted;

    public TestQuestion(String title, String contents) {
        super(title, contents);
        deleted = false;
    }

    @Override
    public List<DeleteHistory> delete(User loginUser) throws CannotDeleteException {
        deleted = true;
        return super.delete(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }
}
