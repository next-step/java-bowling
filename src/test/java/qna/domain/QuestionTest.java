package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("로그인 유저가 작성한 질문이 아닌경우 예외를 반환한다.")
    @Test
    void checkWriter_예외() {
        assertThatThrownBy(() -> Q1.checkWriter(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);

        assertThatThrownBy(() -> Q2.checkWriter(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("로그인 유저가 작성한 질문일 경우 true를 반환한다.")
    @Test
    void checkWriter() throws CannotDeleteException {
        assertThat(Q1.checkWriter(UserTest.JAVAJIGI)).isTrue();
        assertThat(Q2.checkWriter(UserTest.SANJIGI)).isTrue();
    }
}
