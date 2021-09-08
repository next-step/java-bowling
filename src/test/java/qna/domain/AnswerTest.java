package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 삭제할 수 없다")
    void invalidOwner() throws CannotDeleteException {
        User sanjigi = UserTest.SANJIGI;

        assertThrows(CannotDeleteException.class, () -> answer.delete(sanjigi));
    }

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 삭제할 수 없다")
    void validOwner() throws CannotDeleteException {
        User javajigi = UserTest.JAVAJIGI;

        DeleteHistory deleteHistory = assertDoesNotThrow(() -> answer.delete(javajigi));

        assertTrue(answer.isDeleted());
        assertThat(deleteHistory).isNotNull();
    }
}
