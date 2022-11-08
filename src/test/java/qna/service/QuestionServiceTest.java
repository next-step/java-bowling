package qna.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import qna.CannotDeleteException;
import qna.domain.Answer;
import qna.domain.Question;
import qna.domain.QuestionTest;
import qna.domain.UserTest;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @InjectMocks
    private QuestionService questionService;

    private Question question;
    private Answer answer;

    @BeforeEach
    public void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    @DisplayName("작성자와 로그인한 사용자가 다를 경우 예외를 던진다.")
    public void Given_CheckOwner_Then_Throw() {
        assertThatThrownBy(() -> {
            questionService.checkOwnerOrThrow(UserTest.SANJIGI, question);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("작성자와 로그인한 사용자가 같을 경우 예외를 던지지 않는다.")
    public void Given_CheckOwner_Then_NotThrow() {

        assertDoesNotThrow(() -> questionService.checkOwnerOrThrow(UserTest.JAVAJIGI, question));
    }

    @Test
    @DisplayName("isDeleted 필드가 true 로 업데이트된다.")
    public void Given_SoftDelete_Then_Deleted() {
        assertThat(question.isDeleted()).isFalse();
        questionService.executeSoftDelete(UserTest.JAVAJIGI, question);

        assertThat(question.isDeleted()).isTrue();
    }
}
