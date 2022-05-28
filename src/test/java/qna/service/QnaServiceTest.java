package qna.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import qna.CannotDeleteException;
import qna.domain.Answer;
import qna.domain.AnswerTest;
import qna.domain.DeleteQuestionEvent;
import qna.domain.Question;
import qna.domain.QuestionRepository;
import qna.domain.QuestionTest;
import qna.domain.UserTest;

@ExtendWith(MockitoExtension.class)
public class QnaServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QnAService qnAService;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    private Question question;
    private Answer answer;

    @BeforeEach
    public void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    public void delete_성공() {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        assertThat(question.isDeleted()).isFalse();
        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        verify(applicationEventPublisher, times(1)).publishEvent(any(DeleteQuestionEvent.class));
    }

    @DisplayName("질문이 삭제되면 답변도 모두 삭제된다.")
    @Test
    public void delete_success_all_answer_deleted() {
        final Answer answer01 = new Answer(UserTest.JAVAJIGI, question, "answer contents1");
        final Answer answer02 = new Answer(UserTest.JAVAJIGI, question, "answer contents2");
        question.addAnswer(answer01);
        question.addAnswer(answer02);
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        assertAll(
                () -> assertThat(question.isDeleted()).isTrue(),
                () -> assertThat(answer01.isDeleted()).isTrue(),
                () -> assertThat(answer02.isDeleted()).isTrue()
        );
    }
    @Test
    public void delete_실패_다른_사람이_쓴_글() {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class)
          .hasMessage("질문자 본인만 삭제할 수 있습니다.");
    }

    @Test
    public void delete_성공_질문자_답변자_같음() {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        verify(applicationEventPublisher, times(1)).publishEvent(any(DeleteQuestionEvent.class));
    }

    @Test
    public void delete_실패_답변_중_다른_사람이_쓴_글() {
        question.addAnswer(AnswerTest.A2);
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class)
          .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
