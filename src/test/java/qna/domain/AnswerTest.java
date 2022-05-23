package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final DeleteHistory A1_DELETE_HISOTRY = new DeleteHistory(ContentType.ANSWER, A1.getId(), UserTest.JAVAJIGI, LocalDateTime.now());
    public static final DeleteHistory A2_DELETE_HISOTRY = new DeleteHistory(ContentType.ANSWER, AnswerTest.A2.getId(), UserTest.SANJIGI, LocalDateTime.now());

    @Test
    @DisplayName("삭제 처리 및 히스토리 생성이 정상적으로 되는지 확인")
    void delete() {
        DeleteHistory actualHistory = A1.delete();
        DeleteHistory expectHistory = A1_DELETE_HISOTRY;

        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isFalse();
        assertThat(actualHistory).isEqualTo(expectHistory);
    }

    @Test
    @DisplayName("삭제 처리가 정상적으로 되는지 확인")
    void setDeleted() {
        A1.setDeleted(true);
        A2.setDeleted(false);

        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("답변의 작성자인지 판단하는 로직 확인")
    void isOwner() {
        assertThat(A1.isOwner(UserTest.JAVAJIGI)).isTrue();
        assertThat(A2.isOwner(UserTest.JAVAJIGI)).isFalse();
    }
}
