package qna.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void 삭제_체크() {
        assertThat(A1.setDeleted(true).isDeleted()).isTrue();
    }

    @Test
    public void 삭제_실패() {
        assertThat(A1.setDeleted(false).isDeleted()).isFalse();
    }

    @Test
    public void 작성자_여부_성공() {
        assertThat(A1.isOwner(UserTest.JAVAJIGI)).isTrue();
    }

    @Test
    public void 작성자_여부_실패() {
        assertThat(A1.isOwner(UserTest.SANJIGI)).isFalse();
    }

    @Test
    public void 작성자_조회() {
        assertThat(A1.getWriter()).isEqualTo(UserTest.JAVAJIGI);
    }
}
