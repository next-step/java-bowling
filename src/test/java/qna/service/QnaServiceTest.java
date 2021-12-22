package qna.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import qna.CannotDeleteException;
import qna.domain.Answer;
import qna.domain.ContentType;
import qna.domain.DeleteHistory;
import qna.domain.Question;
import qna.domain.QuestionRepository;
import qna.domain.QuestionTest;
import qna.domain.UserTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public void delete_성공_질문자_답변자_로그인_사용자_같음() throws Exception {
        // given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);

        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        // when
        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        // then
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        verifyDeleteHistories(question, answer);
    }

    @Test
    public void delete_성공_답변이_없고_질문자_로그인_사용자_같음_경우() throws Exception {
        // given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);

        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        assertThat(question.isDeleted()).isFalse();

        // when
        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        // then
        assertThat(question.isDeleted()).isTrue();
        verifyDeleteHistories(question, null);
    }

    @Test
    public void delete_다른_사람이_쓴_글() {
        // given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"); // 테스트 데이터 수정 부분
        question.addAnswer(answer);

        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        // when
        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, question.getId());
            // then
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_답변() {
        // given
        Question question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(11L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1"); // 테스트 데이터 수정 부분
        question.addAnswer(answer);

        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        // when
        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());
        // then
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    private void verifyDeleteHistories(Question question, Answer answer) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));

        if (answer != null) {
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }

        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
