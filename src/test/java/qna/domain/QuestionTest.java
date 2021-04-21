package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static qna.TestFixture.JAVAJIGI;
import static qna.TestFixture.SANJIGI;

class QuestionTest {

    Question question;

    @BeforeEach
    void setUp() {
        question = new Question("title1", "contents1").writeBy(JAVAJIGI);
    }

    @Test
    @DisplayName("질문이 완전히 삭제되지 않고 삭제 상태로만 변경된다.")
    void softDelete() {
        // given
        // when
        question.delete(JAVAJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제가 가능하다.")
    void deleteOwnedQuestion() {
        //given
        //when
        question.delete(JAVAJIGI);

        //then
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 예외가 발생한다.")
    void deleteByDifferentUser() {
        assertThatThrownBy(() -> question.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(CannotDeleteException.NO_DELETE_PERMISSION);
    }

    @Test
    @DisplayName("답변이 없는 경우 삭제가 가능하다.")
    void deleteWithoutAnswer() {
        // given
        question = new Question("title", "contents").writeBy(SANJIGI);

        // when
        final DeleteHistories deleteHistories = question.delete(SANJIGI);

        // then
        assertAll(
                () -> assertThat(question.isDeleted()).isTrue(),
                () -> assertThat(deleteHistories.deleteHistories()).hasSize(1)
        );
    }

    @Test
    @DisplayName("질문자와 답변글의 모든 답변자가 같은 경우 삭제가 가능하다.")
    void deleteWithSameOwner() {
        // given
        question = new Question("title", "contents").writeBy(SANJIGI);
        Answer answer = new Answer(SANJIGI, question, "Answers Contents");
        question.addAnswer(answer);

        // when
        question.delete(SANJIGI);

        // then
        assertAll(
                () -> assertThat(question.isDeleted()).isTrue(),
                () -> assertThat(answer.isDeleted()).isTrue()
        );
    }

    @Test
    @DisplayName("답변자가 다른 경우 삭제가 불가능하다.")
    void deleteNotSameOwner() {
        // given
        question.addAnswer(new Answer(JAVAJIGI, question, "Answers Contents1"));
        question.addAnswer(new Answer(SANJIGI, question, "Answers Contents2"));

        // when
        assertThatThrownBy(() -> question.delete(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(CannotDeleteException.DIFFERENT_USERS_ANSWER_CONTAINED);
    }

    @Test
    @DisplayName("삭제시 삭제 기록을 반환한다.")
    void deleteHistories() {
        // given
        question.addAnswer(new Answer(JAVAJIGI, question, "Answers Contents1"));
        question.addAnswer(new Answer(JAVAJIGI, question, "Answers Contents2"));

        // when
        DeleteHistories deleteHistories = question.delete(JAVAJIGI);

        // then
        assertThat(deleteHistories.deleteHistories()).hasSize(3);
    }
}
