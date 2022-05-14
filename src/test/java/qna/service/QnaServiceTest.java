package qna.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qna.CannotDeleteException;
import qna.domain.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QnaServiceTest {
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @InjectMocks
    private QnAService qnAService;

    private Question question;
    private Answer answer;
    private Long questionId;
    private Long answerId;

    @BeforeEach
    public void setUp() throws Exception {
        questionId = 1L;
        question = new Question(questionId, "title1", "contents1", UserTest.JAVAJIGI);
        answerId = 11L;
        answer = new Answer(answerId, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    public void delete_성공() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(questionId))
            .thenReturn(Optional.of(question));

        qnAService.deleteQuestion(UserTest.JAVAJIGI, questionId);

        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    public void delete_실패_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(questionId))
            .thenReturn(Optional.of(question));

        assertThatThrownBy(() -> qnAService.deleteQuestion(UserTest.SANJIGI, questionId))
            .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(questionId))
            .thenReturn(Optional.of(question));

        qnAService.deleteQuestion(UserTest.JAVAJIGI, questionId);

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(questionId)).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, questionId);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
