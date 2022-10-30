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
    void setup() {
        answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }


    @Test
    @DisplayName("작성자가 삭제 요청 시 정상적으로 삭제된다.")
    void deleteWithValidUser() throws CannotDeleteException {
        answer.delete(UserTest.JAVAJIGI);
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 삭제 요청 시 예외를 던진다.")
    void deleteWithInvalidUser() {
        assertThatThrownBy(() -> answer.delete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
