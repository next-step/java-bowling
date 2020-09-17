package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import qna.CannotDeleteException;

@ExtendWith(MockitoExtension.class)
public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final LocalDateTime deleteTime = LocalDateTime.now();

    @Test
    @DisplayName("answer 생성 테스트")
    public void createQuestionTest() throws CannotDeleteException {
        assertThat(A2.getWriter()).isEqualTo(UserTest.SANJIGI);
        assertThat(A2.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("answer 삭제 테스트")
    public void deleteQuestionTest() throws CannotDeleteException {

        //when
        A1.delete(UserTest.JAVAJIGI, deleteTime);

        //then
        assertThat(A1.getWriter()).isEqualTo(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("question 삭제 예외 테스트 : CannotDeleteException")
    public void deleteAnswerExceptionTest() throws CannotDeleteException {
        assertThatThrownBy(()-> A1.delete(UserTest.SANJIGI, deleteTime)).isInstanceOf(CannotDeleteException.class);
    }
}
