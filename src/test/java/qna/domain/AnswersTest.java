package qna.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answers given;

    @Before
    public void setUp() {
        given = new Answers();
        given.add(A1);
    }

    @DisplayName("답변 추가시 사이즈 1증가")
    @Test
    public void add() {
        assertThat(given.value().size()).isEqualTo(1);
    }

    @DisplayName("사용자=답변자: 삭제 후 기록을 남긴다.")
    @Test
    public void delete_user_and_answer_writer_are_same() {
        assertThat(given.delete(UserTest.JAVAJIGI).get(0))
                .isInstanceOf(DeleteHistory.class);
    }

    @DisplayName("사용자!=답변자: 삭제 불가")
    @Test
    public void delete_user_and_answer_writer_are_different() {
        assertThatThrownBy(() -> given.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
