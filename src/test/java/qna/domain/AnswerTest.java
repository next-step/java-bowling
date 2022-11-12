package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

@DisplayName("Answer 테스트")
class AnswerTest {

    Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @Test
    void 로그인_유저가_널일때_삭제를_시도하면_예외를_던진다() {
        assertThatNullPointerException()
            .isThrownBy(
                () -> answer.delete(null)
            );
    }

    @Test
    void 로그인_유저가_본인이면_삭제를_성공한다() {
        assertThatNoException()
            .isThrownBy(
                () -> answer.delete(UserTest.JAVAJIGI)
            );
    }

    @Test
    void 삭제를_성공하면_deleted_값이_true를_갖는다() throws CannotDeleteException {
        answer.delete(UserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    void 로그인_유저가_본인이_아니면_예외를_던진다() {
        assertThatThrownBy(
            () -> answer.delete(UserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
