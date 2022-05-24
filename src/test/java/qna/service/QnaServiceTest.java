package qna.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qna.DeleteQuestionPermissionException;
import qna.domain.ContentType;
import qna.domain.answer.Answer;
import qna.domain.deleteHistory.DeleteHistories;
import qna.domain.deleteHistory.DeleteHistory;
import qna.domain.question.Question;
import qna.domain.question.QuestionRepository;
import qna.domain.question.QuestionTest;
import qna.domain.user.UserTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class QnaServiceTest {
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
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    public void delete_성공() throws Exception {
        given(questionRepository.findByIdAndDeletedFalse(question.getId()))
                .willReturn(Optional.of(question));

        assertThat(question.isDeleted()).isFalse();
        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        given(questionRepository.findByIdAndDeletedFalse(question.getId()))
                .willReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, question.getId());
        }).isInstanceOf(DeleteQuestionPermissionException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        given(questionRepository.findByIdAndDeletedFalse(question.getId()))
                .willReturn(Optional.of(question));

        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        given(questionRepository.findByIdAndDeletedFalse(question.getId()))
                .willReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, question.getId());
        }).isInstanceOf(DeleteQuestionPermissionException.class);
    }

    private void verifyDeleteHistories() {
        List<DeleteHistory> deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        verify(deleteHistoryService).saveAll(new DeleteHistories(deleteHistories));
    }
}
