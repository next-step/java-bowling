package qna.domain;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qna.CannotDeleteException;
import qna.service.DeleteHistoryService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @Test
    @DisplayName("answer 생성 테스트")
    public void createQuestionTest() throws CannotDeleteException {
        assertThat(A2.getWriter()).isEqualTo(UserTest.SANJIGI);
        assertThat(A2.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("answer 삭제 테스트")
    public void deleteQuestionTest() throws CannotDeleteException {
        //given
        doNothing().when(deleteHistoryService).save(any());

        //when
        A1.delete(UserTest.JAVAJIGI, deleteHistoryService);

        //then
        assertThat(A1.getWriter()).isEqualTo(UserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("question 삭제 예외 테스트 : CannotDeleteException")
    public void deleteAnswerExceptionTest() throws CannotDeleteException {
        assertThatThrownBy(()-> A1.delete(UserTest.SANJIGI, deleteHistoryService)).isInstanceOf(CannotDeleteException.class);
    }
}
