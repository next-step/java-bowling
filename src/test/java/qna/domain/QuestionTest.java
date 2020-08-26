package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("질문을 생성한 Owner 여부를 확인함")
    @Test
    void verifyOwner() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(()->Q1.verifyOwner(UserTest.SANJIGI))
        ;
    }
}
