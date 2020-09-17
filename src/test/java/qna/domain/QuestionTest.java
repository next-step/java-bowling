package qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import qna.CannotDeleteException;

@ExtendWith(MockitoExtension.class)
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    public static final LocalDateTime deleteTime = LocalDateTime.now();

    @Test
    @DisplayName("question 생성 테스트")
    public void createQuestionTest() throws CannotDeleteException {
        assertThat(Q2.getWriter()).isEqualTo(UserTest.SANJIGI);
        assertThat(Q2.getWriter().getId()).isEqualTo(UserTest.SANJIGI.getId());
        assertThat(Q2.getTitle()).isEqualTo("title2");
        assertThat(Q2.getContents()).isEqualTo("contents2");
        assertThat(Q2.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("question 삭제 테스트")
    public void deleteQuestionTest() throws CannotDeleteException {
        Q1.delete(UserTest.JAVAJIGI, deleteTime);

        assertThat(Q1.getWriter()).isEqualTo(UserTest.JAVAJIGI);
        assertThat(Q1.getWriter().getId()).isEqualTo(UserTest.JAVAJIGI.getId());
        assertThat(Q1.getTitle()).isEqualTo("title1");
        assertThat(Q1.getContents()).isEqualTo("contents1");
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("question 삭제 예외 테스트 : CannotDeleteException")
    public void deleteQuestionExceptionTest() throws CannotDeleteException {

        assertThatThrownBy(()-> Q1.delete(UserTest.SANJIGI, deleteTime)).isInstanceOf(CannotDeleteException.class);
    }
}
