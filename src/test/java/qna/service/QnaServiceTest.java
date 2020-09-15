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
    private User user;

    @Before
    public void setUp() throws Exception {
        question = Question.ofQuestion(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = Answer.ofAnswer(11L, UserTest.JAVAJIGI, QuestionTest.Q1,"Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    @DisplayName("Question 삭제 테스트")
    public void delete_성공() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));
        assertThat(question.isDeleted()).isFalse();

        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    @DisplayName("다른 사람이 작성한 Question 삭제 테스트")
    public void delete_다른_사람이_쓴_글() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("Question 작성자와 답변자가 같은 경우 테스트")
    public void delete_성공_질문자_답변자_같음() throws Exception {

        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {

        // given
        User otherAnswerWriter = User.ofUser(2L, "kjyang", "Password", "Name", "slamdunk7575@slipp.net");
        Answer answer = Answer.ofAnswer(null, otherAnswerWriter, question,"Content of answer");
        question.addAnswer(answer);

        // when
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        // then
        assertThatThrownBy(() -> qnAService.deleteQuestion(question.getWriter(), question.getId()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("답변을 삭제할 권한이 없습니다");
    }

    private void verifyDeleteHistories() {
        DeleteHistory questionHistory = DeleteHistory.ofQuestion(question.getId(), question.getWriter());
        DeleteHistory answerHistory = DeleteHistory.ofAnswer(answer.getId(), answer.getWriter());

        List<DeleteHistory> deleteHistories = Arrays.asList(questionHistory, answerHistory);
        verify(deleteHistoryService).saveAll(deleteHistories);
    }

}
