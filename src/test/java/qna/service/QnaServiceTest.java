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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static qna.domain.Answer.newAnswer;
import static qna.domain.DeleteHistory.newDeleteHistory;
import static qna.domain.Question.*;
import static qna.domain.QuestionTest.Q1;
import static qna.domain.UserTest.JAVAJIGI;

@ExtendWith(MockitoExtension.class)
public class QnaServiceTest {
    public static final Question DELETED_QUESTION = newQuestionWithDeleted("title1", "contents1", true).writeBy(JAVAJIGI);

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @InjectMocks
    private QnAService qnAService;

    private Question question;
    private Answer answer;

    @BeforeEach
    public void setUp() throws Exception {
        question = newQuestion("title1", "contents1").writeBy(JAVAJIGI);
        answer = newAnswer(JAVAJIGI, Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    public void delete_성공() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.Id())).thenReturn(
            Optional.of(question));

        assertThat(question).isEqualTo(DELETED_QUESTION);
        qnAService.deleteQuestion(JAVAJIGI, question.Id());

        assertThat(question).isEqualTo(DELETED_QUESTION);
        verifyDeleteHistories();
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.Id())).thenReturn(
            Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, question.Id());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.Id())).thenReturn(
            Optional.of(question));

        qnAService.deleteQuestion(JAVAJIGI, question.Id());

        assertThat(question).isEqualTo(DELETED_QUESTION);
        assertThat(question).isEqualTo(DELETED_QUESTION);
        verifyDeleteHistories();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.Id())).thenReturn(
            Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, question.Id());
        }).isInstanceOf(CannotDeleteException.class);
    }

    private void verifyDeleteHistories() {
        DeleteHistories deleteHistories = DeleteHistories.deleteHistories(Arrays.asList(
            newDeleteHistory(ContentType.QUESTION, question.Id(), question.getWriter(),
                LocalDateTime.now()),

            newDeleteHistory(ContentType.ANSWER, answer.Id(), answer.getWriter(),
                LocalDateTime.now())));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
