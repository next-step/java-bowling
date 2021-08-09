package qna.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import qna.domain.*;
import qna.domain.answer.Answer;
import qna.domain.history.DeleteHistory;
import qna.domain.question.Question;
import qna.domain.question.QuestionRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Before
    public void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @DisplayName("deleteHistories 등록 테스트")
    @Test
    public void delete_성공() {
        when(questionRepository.findByIdAndDeletedFalse(question.id()))
                .thenReturn(Optional.of(question));

        assertThat(question.isDeleted()).isFalse();
        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.id());
        assertThat(question.isDeleted()).isTrue();

        verifyDeleteHistories();
    }

    private void verifyDeleteHistories() {
        List<DeleteHistory> deleteHistories = Arrays.asList(
                DeleteHistory.of(question, UserTest.JAVAJIGI),
                DeleteHistory.of(answer, UserTest.JAVAJIGI));
        verify(deleteHistoryService).saveAll(deleteHistories);
    }
}
