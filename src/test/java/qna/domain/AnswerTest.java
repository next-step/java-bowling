package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.QuestionTest.*;
import static qna.domain.UserTest.JAVAJIGI;
import static qna.domain.UserTest.SANJIGI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 객체가 정상적으로 생성되었는지 확인한다.")
    void checkedCreateAnswerObject() {
        // given & when
        Answer answer = new Answer(JAVAJIGI, Q1, "Answers Contents3");

        // then
        assertThat(answer).isEqualTo(new Answer(JAVAJIGI, Q1, "Answers Contents3"));
    }

    @Test
    @DisplayName("삭제되지 않은 답변의 상태는 False를 갖는다.")
    void checkedNonDeleteAnswerStateIsFalse() {
        // then
        assertThat(A2.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("접근하는 로그인 유저와 답변 유저가 다른 경우, 예외처리를 한다.")
    void exceptionLoginUserDifferentAnswerUser() {
        // given
        User loginUser = SANJIGI;

        // when & then
        assertThatThrownBy(() -> A1.delete(loginUser))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변을 삭제한 후의 상태는 True를 갖는다.")
    void checkedDeleteAnswerStateIsTrue() {
        // given
        User loginUser = JAVAJIGI;

        // when
        A1.delete(loginUser);

        // then
        assertThat(A1.isDeleted()).isTrue();
    }
}
