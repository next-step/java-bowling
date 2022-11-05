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

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QnaServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @Mock
    private QuestionService questionService;

    @Mock
    private AnswerService answerService;

    @InjectMocks
    private QnAService qnAService;

    private Question question;
    private Question deletedQuestion;
    private Answer answer;
    private Answer deletedAnswer;

    @BeforeEach
    public void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        deletedQuestion = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        deletedQuestion.setDeleted(true);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        deletedAnswer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        deletedAnswer.setDeleted(true);
        question.addAnswer(answer);
        deletedQuestion.addAnswer(deletedAnswer);
    }

    @Test
    public void delete_성공() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        verifyDeleteHistories();
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));
        when(questionService.delete(question, UserTest.SANJIGI)).thenThrow(new CannotDeleteException("질문을 삭제할 권한이 없습니다."));

        assertThatThrownBy(() ->
                qnAService.deleteQuestion(UserTest.SANJIGI, question.getId())).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));
        when(answerService.deleteAll(List.of(answer), UserTest.SANJIGI)).thenThrow(new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다."));

        assertThatThrownBy(() ->
                qnAService.deleteQuestion(UserTest.SANJIGI, question.getId())).isInstanceOf(CannotDeleteException.class);
    }

    private void verifyDeleteHistories() {
        DeleteHistories deleteHistories = new DeleteHistories(
                Arrays.asList(
                        new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now())));

        verify(deleteHistoryService).saveAll(deleteHistories.getDeleteHistories());
    }
}
