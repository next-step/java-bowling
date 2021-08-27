package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("create test")
    @Test
    void create() {
        Answer testAnswer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        assertThat(testAnswer).isEqualTo(new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
    }

    @DisplayName("setDeleted 함수 테스트")
    @Test
    void setDeleted() {
        A1.setDeleted(false);

        assertThat(A1.isDeleted()).isFalse();
    }

    @DisplayName("delete 함수 테스트")
    @Test
    void delete() throws CannotDeleteException {
        A2.delete(UserTest.SANJIGI);

        assertThat(A2.isDeleted()).isTrue();
    }

    @DisplayName("인가 되지 않은 사용자가 delete 할때 Exception 테스트")
    @Test
    void deleteException() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> {
                    A1.delete(UserTest.SANJIGI);
                }).withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("isDeleted 테스트")
    @Test
    void isDeleted() {
        A1.setDeleted(true);

        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("작성자가 맞는지 테스트")
    @Test
    void isOwner() {
        assertThat(A1.isOwner(UserTest.JAVAJIGI)).isTrue();
    }

    @DisplayName("답변의 작성자 가져오기 테스트")
    @Test
    void getWriter() {
        assertThat(A2.getWriter()).isEqualTo(UserTest.SANJIGI);
    }

    @DisplayName("답변 내용 테스트")
    @Test
    void getContents() {
        assertThat(A1.getContents()).isEqualTo("Answers Contents1");
    }
}
