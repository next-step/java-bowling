package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void When_Delete_Then_Deleted() {
        assertDoesNotThrow(() -> Q1.delete(UserTest.JAVAJIGI));
    }

    @Test
    void Given_WrongUser_When_Delete_Then_CannotDeleteException() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> Q1.delete(UserTest.SANJIGI));
    }
}
