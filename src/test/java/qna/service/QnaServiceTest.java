package qna.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
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

@RunWith(MockitoJUnitRunner.class)
public class QnaServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @InjectMocks
    private QnAService qnAService;

    @Test
    public void delete_성공() throws Exception {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        when(questionRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(question));

        assertThat(question.isDeleted()).isFalse();
        qnAService.deleteQuestion(UserTest.JAVAJIGI, 1L);

        assertThat(question.isDeleted()).isTrue();
        List<DeleteHistory> deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, 1L, question.getWriter(), LocalDateTime.now()));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        when(questionRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, 1L);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
        when(questionRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(question));

        qnAService.deleteQuestion(UserTest.JAVAJIGI, 1L);

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        List<DeleteHistory> deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, 1L, question.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, 11L, answer.getWriter(), LocalDateTime.now()));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        Question question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(11L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
        when(questionRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.JAVAJIGI, 1L);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
