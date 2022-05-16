package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    @Test
    @DisplayName("질문 유저와 접속 유저가 다른 경우, 예외처리를 한다.")
    void exceptionLoginUserDifferentQuestionUser() {
        // give
        User loginUser = SANJIGI;

        // when & then
        assertThatThrownBy(() -> Q1.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문이 삭제된 경우, 질문 삭제 상태가 True가 된다.")
    void checkedQuestionDeleteStateIsTrue() {
        // given
        User loginUser = JAVAJIGI;

        // when
        Q1.delete(loginUser);

        // then
        assertThat(Q1.isDeleted()).isTrue();
    }
}
