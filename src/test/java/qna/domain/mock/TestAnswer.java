package qna.domain.mock;

import qna.domain.Answer;
import qna.domain.DeleteHistory;
import qna.domain.Question;
import qna.domain.User;

public class TestAnswer extends Answer {
    private boolean deleted;

    public TestAnswer(User writer, Question question, String contents) {
        super(writer, question, contents);
        deleted = false;
    }

    @Override
    public DeleteHistory delete(User loginUser) {
        deleted = true;
        return super.delete(loginUser);
    }

    public boolean isDeleted() {
        return deleted;
    }
}
