package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void delete() {
        //given
        //when
        Q1.delete();
        //then
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문을 삭제할때, 작성자가 아닌 유저라면 exception")
    void checkOwner_fail() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> Q1.checkOwner(UserTest.SANJIGI))
            .withMessage("질문을 삭제할 권한이 없습니다.");
    }
}
