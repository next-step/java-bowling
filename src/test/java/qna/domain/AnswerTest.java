package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("답변 테스트")
class AnswerTest {
    public static final Answer A1_OF_JAVAJIGI = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2_OF_SANGJIGI = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 생성")
    @Test
    void createTest() {
        // then
        assertThat(A1_OF_JAVAJIGI).isEqualTo(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
    }

    @DisplayName("답변 내용 확인")
    @Test
    void getContentsTest() {
        // when & then
        assertThat(A1_OF_JAVAJIGI.getContents()).isEqualTo("Answers Contents1");
    }

    @DisplayName("답변 삭제")
    @Test
    void deleteTest() {
        // given
        User javajigi = UserTest.JAVAJIGI;
        Answer answer = new Answer(javajigi, QuestionTest.Q1, "What is Justice?");
        // when & then
        assertThat(answer.isDeleted()).isFalse();
        // when
        answer.delete(javajigi);
        // then
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("답변 삭제 실패 - 다른 작성자의 답변을 삭제하려는 경우")
    @Test
    void deleteFailedTest() {
        // given
        Answer answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "What is Justice?");
        // when & then
        assertThat(answer.isDeleted()).isFalse();
        // when
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> answer.delete(UserTest.SANJIGI))
                .withMessageContaining("다른 작성자의 답변을 삭제할 수 없습니다.");
    }

    @DisplayName("답변 작성자 반환")
    @Test
    void getWriterTest() {
        // when & then
        assertThat(A2_OF_SANGJIGI.getWriter()).isEqualTo(UserTest.SANJIGI);
    }

    @DisplayName("답변 주인 확인")
    @Test
    void isOwnerTest() {
        // when & then
        assertAll(
                () -> assertThat(A1_OF_JAVAJIGI.isOwner(UserTest.JAVAJIGI)).isTrue(),
                () -> assertThat(A1_OF_JAVAJIGI.isOwner(UserTest.SANJIGI)).isFalse()
        );
    }
}
