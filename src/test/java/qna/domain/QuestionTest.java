package qna.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1", UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2", UserTest.SANJIGI);

    @DisplayName("질문을 삭제하고 해당 질문을 반환한다.")
    @Test
    void 질문을_삭제하고_이를_반환한다() throws CannotDeleteException {
        Question deletedTheFirst = Q1.delete(UserTest.JAVAJIGI);
        Question deletedTheSecond = Q2.delete(UserTest.SANJIGI);

        assertAll(
            () -> assertThat(deletedTheFirst).isEqualTo(Q1),
            () -> assertThat(deletedTheSecond).isEqualTo(Q2)
        );
    }
}
