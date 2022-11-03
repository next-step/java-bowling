package qna.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qna.CannotDeleteException;
import qna.domain.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void setUp() {
        question = new Question(1L, new QuestionBody("title1", "contents1"))
                .writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, new ArticleInfo(UserTest.JAVAJIGI), new AnswerBody(QuestionTest.Q1, "Answers Contents1"));
        question.addAnswer(answer);
    }

    @Test
    public void delete_성공() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.getId()))
                .thenReturn(Optional.of(question));

        assertThat(question.isDeleted()).isFalse();
        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    public void delete_다른_사람이_쓴_글() {
        when(questionRepository.findByIdAndDeletedFalse(question.getId()))
                .thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        when(questionRepository.findByIdAndDeletedFalse(question.getId()))
                .thenReturn(Optional.of(question));

        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        verifyDeleteHistories();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() {
        when(questionRepository.findByIdAndDeletedFalse(question.getId()))
                .thenReturn(Optional.of(question));

        assertThatThrownBy(() -> qnAService.deleteQuestion(UserTest.SANJIGI, question.getId()))
                .isInstanceOf(CannotDeleteException.class);
    }

    private void verifyDeleteHistories() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
