package qna.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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

/**
 * 해당 테스트 케이스가 깨지지 않도록 서비스의 비즈니스 로직을 도메인으로 이동하는 것이 목표
 */
@RunWith(MockitoJUnitRunner.class)
public class QnaServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @InjectMocks
    private QnAService qnAService;

    private Question question;
    private Answer answer;

    @Before
    public void setUp() throws Exception {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @DisplayName("QnA 게시물 삭제처리 테스트")
    @Test
    public void delete_성공() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        assertThat(question.isDeleted()).isFalse();
        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @DisplayName("QnA 게시물 권한 체크 예외처리 테스트")
    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("QnA 게시물 삭제처리 기능 테스트")
    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @DisplayName("QnA 게시물 내에 다른 작성자가 쓴 답변으로 인하여 게시물 삭제 불가 테스트")
    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }

    private void verifyDeleteHistories() {
        List<DeleteHistory> deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.writer(), LocalDateTime.now()));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
