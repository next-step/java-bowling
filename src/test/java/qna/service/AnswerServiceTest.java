package qna.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import qna.CannotDeleteException;
import qna.domain.Answer;
import qna.domain.QuestionTest;
import qna.domain.UserTest;

@ExtendWith(MockitoExtension.class)
public class AnswerServiceTest {

    @InjectMocks
    private AnswerService answerService;

    List<Answer> answers;

    @BeforeEach
    public void setUp() {
        answers = List.of(
            new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"),
            new Answer(2L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2"));
    }

    @Test
    @DisplayName("작성자와 로그인한 사용자가 다를 경우 예외를 던진다.")
    public void Given_CheckOwner_Then_Throw() {
        assertThatThrownBy(() -> {
            answerService.checkOwnerOrThrow(UserTest.SANJIGI, answers);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("작성자와 로그인한 사용자가 같을 경우 예외를 던지지 않는다.")
    public void Given_CheckOwner_Then_NotThrow() {

        assertDoesNotThrow(() -> answerService.checkOwnerOrThrow(UserTest.JAVAJIGI, answers));
    }

    @Test
    @DisplayName("isDeleted 필드가 true 로 업데이트된다.")
    public void Given_SoftDelete_Then_Deleted() {
        answerService.executeSoftDelete(UserTest.JAVAJIGI, answers);

        assertThat(answers).allSatisfy(answer -> assertThat(answer.isDeleted()).isTrue());
    }
}
