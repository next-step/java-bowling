package qna.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("로그인 유저와 작성자가 같지 않을 경우 예외 처리")
    void checkUser() {
        Assertions.assertThrows(CannotDeleteException.class,
                () -> A1.checkWriter(UserTest.SANJIGI));
    }
}
