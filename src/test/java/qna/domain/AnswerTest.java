package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "contents1");
    }

    @Test
    @DisplayName("유효하지 않은 삭제 테스트 : 내 게시글이 아닐 때")
    void invalidDeleteTest() {
        assertThatThrownBy(() -> answer.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class).hasMessage("다른사람의 답변이라 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("유효한 삭제 테스트")
    void validDeleteTest() {
        answer.delete(UserTest.JAVAJIGI);
        assertThat(answer.isDeleted()).isTrue();
    }
}
