package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 삭제 테스트")
    void deleteTest() {
        A1.setDeleted(true);
        assertThat(A1.isDeleted()).isTrue();

        A1.setDeleted(false);
        assertThat(A1.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("권한 테스트")
    void isOwnerTest() {
        assertThat(A1.isOwner(UserTest.JAVAJIGI)).isTrue();
        assertThat(A1.isOwner(UserTest.SANJIGI)).isFalse();

        assertThat(A2.isOwner(UserTest.JAVAJIGI)).isFalse();
        assertThat(A2.isOwner(UserTest.SANJIGI)).isTrue();
    }
}
