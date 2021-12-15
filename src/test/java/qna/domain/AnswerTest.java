package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("답변 테스트")
class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 생성")
    @Test
    void createTest() {
        // then
        assertThat(A1).isEqualTo(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
    }

    @DisplayName("답변 내용 확인")
    @Test
    void getContentsTest() {
        // when & then
        assertThat(A1.getContents()).isEqualTo("Answers Contents1");
    }

    @DisplayName("답변 삭제")
    @Test
    void deleteTest() {
        // given
        Answer answer = new Answer();
        // when & then
        assertThat(answer.isDeleted()).isFalse();
        answer.setDeleted(true);
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("답변 작성자 반환")
    @Test
    void getWriterTest() {
        // when & then
        assertThat(A2.getWriter()).isEqualTo(UserTest.SANJIGI);
    }

    @DisplayName("답변 주인 확인")
    @Test
    void isOwnerTest() {
        // when & then
        assertAll(
                () -> assertThat(A1.isOwner(UserTest.JAVAJIGI)).isTrue(),
                () -> assertThat(A1.isOwner(UserTest.SANJIGI)).isFalse()
        );
    }
}
