package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void create_답변_생성() {
        assertThat(A1.getWriter()).isEqualTo(UserTest.JAVAJIGI);
        assertThat(A1.getContents()).isEqualTo("Answers Contents1");
    }

    @Test
    void delete_답변_삭제_성공() throws CannotDeleteException {
        assertThat(A2.isDeleted()).isFalse();
        A2.delete(UserTest.SANJIGI);
        assertThat(A2.isDeleted()).isTrue();
    }

    @Test
    void delete_답변_삭제_실패() {
        assertThatThrownBy(() -> A1.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void isOwner_답변_작성자_확인() {
        assertAll(
                () -> assertThat(A1.isOwner(UserTest.JAVAJIGI)).isTrue(),
                () -> assertThat(A1.isOwner(UserTest.SANJIGI)).isFalse()
        );
    }
}
