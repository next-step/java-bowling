package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("로그인 유저가 질문 삭제 가능한지 체크")
    @Test
    public void delete() throws Exception {
        //given
        Q1.validateDeleteAble(UserTest.JAVAJIGI);
    }

    @DisplayName("로그인 유저가 질문 쓴사람 아니면 exception")
    @Test
    public void delete_fail() throws Exception {
        //given
        assertThatThrownBy(
                () -> Q1.validateDeleteAble(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
