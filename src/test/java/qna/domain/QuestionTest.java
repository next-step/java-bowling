package qna.domain;

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
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Mock
    private DeleteHistoryService deleteHistoryService;

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
        doNothing().when(deleteHistoryService).save(any());

        Q1.delete(UserTest.JAVAJIGI, deleteHistoryService);

        assertThat(Q1.getWriter()).isEqualTo(UserTest.JAVAJIGI);
        assertThat(Q1.getWriter().getId()).isEqualTo(UserTest.JAVAJIGI.getId());
        assertThat(Q1.getTitle()).isEqualTo("title1");
        assertThat(Q1.getContents()).isEqualTo("contents1");
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("question 삭제 예외 테스트 : CannotDeleteException")
    public void deleteQuestionExceptionTest() throws CannotDeleteException {
        assertThatThrownBy(()-> Q1.delete(UserTest.SANJIGI, deleteHistoryService)).isInstanceOf(CannotDeleteException.class);
    }
}
