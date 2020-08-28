package qna.service;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import qna.CannotDeleteException;
import qna.domain.*;
import qna.fixture.AnswerFixture;
import qna.fixture.QuestionFixture;
import qna.fixture.UserFixture;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QnaServiceTest {
    @InjectMocks
    private QnAService qnAService;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @DisplayName("Question 이 정상적으로 삭제")
    @Test
    public void deleteQuestionTest() {
        // given
        User owner = UserFixture.make(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        Question question = QuestionFixture.make(1L, "Title of question", "Contents of question", owner);

        // and
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));
        assertThat(question.isDeleted()).isFalse();

        // when
        qnAService.deleteQuestion(owner, question.getId());

        // then
        assertThat(question.isDeleted()).isTrue();
        verifyDeleteHistories(question);
    }

    @DisplayName("Question 작성자와 다를 경우 삭제 불가능하고 예외 발생")
    @Test
    public void deleteQuestionOtherOwnerTest() {
        // given
        User owner = UserFixture.make(1L, "javajigi", "Password", "Name", "javajigi@slipp.net");
        Question question = QuestionFixture.make(1L, "Title of question", "Contents of question", owner);

        User other = UserFixture.make(2L, "sanjigi", "Password", "Name", "sanjigi@slipp.net");
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        // when & then
        assertThatThrownBy(() -> qnAService.deleteQuestion(other, question.getId()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다");
    }

    @DisplayName("Question 작성자와 답변자가 모두 같을 경우 Question 과 Answer 모두 삭제")
    @Test
    public void deleteQuestionAndAnswerTest() {
        // given
        User owner = UserFixture.make(1L, "javajigi", "Password", "Name", "javajigi@slipp.net");
        Question question = QuestionFixture.make(1L, "Title of question", "Contents of question", owner);
        Answer answer = AnswerFixture.make(owner, question, "Content of answer");
        question.addAnswer(answer);

        // and
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        // when
        qnAService.deleteQuestion(owner, question.getId());

        // then
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        verifyDeleteHistories(question, answer);
    }

    @DisplayName("Question 작성자와 답변자가 다를 경우 삭제 불가능 하고 예외 발생")
    @Test
    public void deleteQuestionAndAnswerOtherOwnerTest() {
        // given
        User owner = UserFixture.make(1L, "javajigi", "Password", "Name", "javajigi@slipp.net");
        User other = UserFixture.make(2L, "sanjigi", "Password", "Name", "sanjigi@slipp.net");

        Question question = QuestionFixture.make(1L, "Title of question", "Contents of question", owner);
        Answer answer = AnswerFixture.make(other, question, "Content of answer");
        question.addAnswer(answer);

        // and
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> qnAService.deleteQuestion(owner, question.getId()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("답변을 삭제할 권한이 없습니다");
    }

    private void verifyDeleteHistories(Question question) {
        List<DeleteHistory> deleteHistories = Arrays.asList(
                DeleteHistory.of(ContentType.QUESTION, question.getId(), question.getWriter()));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }

    private void verifyDeleteHistories(Question question, Answer answer) {
        List<DeleteHistory> deleteHistories = Arrays.asList(
                DeleteHistory.of(ContentType.QUESTION, question.getId(), question.getWriter()),
                DeleteHistory.of(ContentType.ANSWER, answer.getId(), answer.getWriter()));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
