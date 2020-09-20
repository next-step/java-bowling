package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(12L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    public static Answer ofUser(Long id, User answer_user) {
       return new Answer(id, answer_user, QuestionTest.Q1, "답글 : ^^");
    }

    @Test
    void delete_answer_성공() throws CannotDeleteException {
        Answer answer = ofUser(11L, UserTest.JAVAJIGI);
        System.out.println(answer);
        answer.deleteAnswer(UserTest.JAVAJIGI);
    }
}
