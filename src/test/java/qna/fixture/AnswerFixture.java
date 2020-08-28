package qna.fixture;

import qna.domain.Answer;
import qna.domain.Question;
import qna.domain.User;

public class AnswerFixture {
    private AnswerFixture() {}

    public static Answer make(User user, Question question, String content) {
        return new Answer(user, question, content);
    }
}
