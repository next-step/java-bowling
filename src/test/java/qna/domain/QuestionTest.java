package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("로그인 한 사용자와 질문 작성자가 같으면 true를 반환한다")
    void shouldReturnTrueWhenOwner() throws CannotDeleteException {
        boolean result = Q1.isOwner(UserTest.JAVAJIGI);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("로그인 한 사용자와 질문 작성자가 다르면 예외를 던진다")
    void shouldThrowWhenNotSameUser() {
        assertThatThrownBy(() -> Q1.isOwner(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
