package qna.fixture;

import qna.domain.Question;
import qna.domain.User;

public class QuestionFixture {
    private QuestionFixture() {}

    public static Question make(Long id, String title, String contents, User writer) {
        return new Question(id, title, contents).writeBy(writer);
    }
}
