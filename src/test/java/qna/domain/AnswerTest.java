package qna.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @DisplayName("오너가 일치하는지 테스트")
    @Test
    void onwerTest() {
        assertThrows(CannotDeleteException.class, () -> A1.deleted(UserTest.SANJIGI));
    }

    @DisplayName("정상적으로 DeleteHistory를 반환하는지 테스트")
    @Test
    void deleteTest() throws CannotDeleteException {
        DeleteHistory deleteHistory = A1.deleted(UserTest.JAVAJIGI);
        DeleteHistory expectedDeleteHistory =
                new DeleteHistory(
                        ContentType.ANSWER,
                        A1.getId(),
                        A1.getWriter(),
                        LocalDateTime.now());

        assertEquals(deleteHistory, expectedDeleteHistory);
    }

}
