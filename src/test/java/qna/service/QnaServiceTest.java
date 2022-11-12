package qna.service;

import org.hibernate.sql.Delete;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qna.CannotDeleteException;
import qna.domain.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static qna.domain.DeleteHistories.deleteHistories;
import static qna.domain.DeleteHistory.newDeleteHistory;
import static qna.domain.Question.*;
import static qna.domain.UserTest.JAVAJIGI;

@ExtendWith(MockitoExtension.class)
public class QnaServiceTest {

    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private DeleteHistoryService deleteHistoryService;

    @InjectMocks
    private QnAService qnAService;

    private Question deleteQuestion;
    private Answer answer;
    private Question Q1;

    @BeforeEach
    public void setUpAnswer() throws Exception {
        Q1 = newQuestion(1L, "title1", "contents1").writeBy(JAVAJIGI);

        answer = Answer.newAnswerWithDeleted(1L, JAVAJIGI, Q1, "test",true);
        Q1.addAnswer(answer);

        deleteQuestion = newQuestionWithDeleted(1L, "title1", "contents1", true).writeBy(JAVAJIGI);
        deleteQuestion.addAnswer(answer);

    }

    @Test
    public void delete_성공() throws Exception {

        when(questionRepository.findByIdAndDeletedFalse(Q1.Id())).thenReturn(Optional.of(Q1));

        qnAService.deleteQuestion(JAVAJIGI, Q1.Id());

        assertThat(Q1).isEqualTo(deleteQuestion);
        verifyDeleteHistories();
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(Q1.Id())).thenReturn(
            Optional.of(Q1));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, Q1.Id());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(Q1.Id())).thenReturn(
            Optional.of(Q1));

        qnAService.deleteQuestion(JAVAJIGI, Q1.Id());

        assertThat(Q1).isEqualTo(deleteQuestion);
        verifyDeleteHistories();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(Q1.Id())).thenReturn(
            Optional.of(Q1));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, Q1.Id());
        }).isInstanceOf(CannotDeleteException.class);
    }

    private void verifyDeleteHistories() {
        List<DeleteHistory> deleteHistories = Arrays.asList(
            newDeleteHistory(ContentType.QUESTION, Q1.Id(), Q1.getWriter(), LocalDateTime.now()),
            newDeleteHistory(ContentType.ANSWER, answer.Id(), answer.getWriter(), LocalDateTime.now()));

        verify(deleteHistoryService).saveAll(deleteHistories(deleteHistories));
    }
}
