package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.TestFixture;
import qna.exception.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuestionTest {

    Question question;

    @BeforeEach
    void setUp() {
        question = TestFixture.Q1;
    }

    @Test
    @DisplayName("질문이 완전히 삭제되지 않고 삭제 상태로만 변경된다.")
    void softDelete() {
        // given
        // when
        question.delete(TestFixture.JAVAJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제가 가능하다.")
    void deleteOwnedQuestion() {
        //given
        //when
        question.delete(TestFixture.JAVAJIGI);

        //then
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 예외가 발생한다.")
    void deleteByDifferentUser() {
        assertThatThrownBy(() -> question.delete(TestFixture.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(CannotDeleteException.NO_DELETE_PERMISSION);
    }

    @Test
    @DisplayName("답변이 없는 경우 삭제가 가능하다.")
    void deleteWithoutAnswer() {
        // given
        question = TestFixture.Q2;

        // when
        question.delete(TestFixture.SANJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
    }
}
