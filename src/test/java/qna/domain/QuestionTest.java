package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("로그인 유저가 질문 작성자이면 예외가 발생하지 않음")
    @Test
    void validateCanDeleteWhenLoginUserIsWriter() {
        User loginUser = UserTest.JAVAJIGI;

        assertThatCode(() -> {
            Q1.delete(loginUser);
        }).doesNotThrowAnyException();
    }

    @DisplayName("로그인 유저가 질문 작성자가 아니면 예외 발생")
    @Test
    void validateCanDeleteWhenLoginUserIsNotWriter(){
        User loginUser = UserTest.SANJIGI;

        assertThatIllegalArgumentException().isThrownBy(() -> {
            Q1.delete(loginUser);
        });
    }
}
