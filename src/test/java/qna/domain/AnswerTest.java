package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;

    @BeforeEach
    void init() {
        answer = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "contents1");
    }

    @Test
    void 삭제_전_테스트() {
        assertThat(answer.isDeleted()).isFalse();
    }

    @Test
    void 질문자_답자_다름_유효성_테스트() {
        assertThatThrownBy(() -> answer.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 삭제_테스트() {
        // when
        answer.delete(UserTest.JAVAJIGI);
        // then
        assertThat(answer.isDeleted()).isTrue();
    }
}
