package qna.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qna.TestFixture;
import qna.domain.*;
import qna.exception.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class QnaServiceTest {
    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private DeleteHistoryService deleteHistoryService;

    @InjectMocks
    private QnAService qnAService;

    private Question question;
    private Answer answer;

    @BeforeEach
    void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(TestFixture.JAVAJIGI);
        answer = new Answer(11L, TestFixture.JAVAJIGI, TestFixture.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    void delete_성공() {
        given(questionRepository.findByIdAndDeletedFalse(question.getId())).willReturn(Optional.of(question));

        assertThat(question.isDeleted()).isFalse();
        qnAService.deleteQuestion(TestFixture.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        given(questionRepository.findByIdAndDeletedFalse(question.getId())).willReturn(Optional.of(question));

        assertThatThrownBy(() -> qnAService.deleteQuestion(TestFixture.SANJIGI, question.getId()))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_성공_질문자_답변자_같음() {
        given(questionRepository.findByIdAndDeletedFalse(question.getId())).willReturn(Optional.of(question));

        qnAService.deleteQuestion(TestFixture.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    void delete_답변_중_다른_사람이_쓴_글() {
        given(questionRepository.findByIdAndDeletedFalse(question.getId())).willReturn(Optional.of(question));

        assertThatThrownBy(() -> qnAService.deleteQuestion(TestFixture.SANJIGI, question.getId()))
                .isInstanceOf(CannotDeleteException.class);
    }

    private void verifyDeleteHistories() {
        List<DeleteHistory> deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        then(deleteHistoryService).should().saveAll(deleteHistories);
    }
}
