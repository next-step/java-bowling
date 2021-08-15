package qna.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import qna.domain.entity.Answer;
import qna.domain.entity.Question;
import qna.domain.exception.CannotDeleteException;
import qna.domain.repository.QuestionRepository;
import qna.domain.testdata.TestData;

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
        question = new Question(1L, "title1", "contents1").writeBy(TestData.JAVAJIGI);
        answer = new Answer(11L, TestData.JAVAJIGI, TestData.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @DisplayName("[성공] delete")
    @Test
    public void delete() {
        // given
        given(questionRepository.findByIdAndDeletedFalse(question.getId()))
            .willReturn(Optional.of(question));
        assertThat(question.isDeleted()).isFalse();

        // when
        qnAService.deleteQuestion(TestData.JAVAJIGI, question.getId());

        // then
        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("[실패] delete - 다른 사람이 쓴 글")
    @Test
    public void delete_otherWriter() {
        // given
        given(questionRepository.findByIdAndDeletedFalse(question.getId()))
            .willReturn(Optional.of(question));

        // when
        assertThrows(CannotDeleteException.class, () -> qnAService.deleteQuestion(TestData.SANJIGI, question.getId()));

        // then
    }

    @DisplayName("[성공] delete - 질문자 답변자 같음")
    @Test
    public void delete_sameAnswerWriter() {
        // given
        given(questionRepository.findByIdAndDeletedFalse(question.getId()))
            .willReturn(Optional.of(question));

        // when
        qnAService.deleteQuestion(TestData.JAVAJIGI, question.getId());

        // then
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("[실패] delete - 질문자 답변자 다름")
    @Test
    public void delete_differentAnswerWriter() {
        // given
        given(questionRepository.findByIdAndDeletedFalse(question.getId()))
            .willReturn(Optional.of(question));

        // when
        assertThrows(CannotDeleteException.class, () -> qnAService.deleteQuestion(TestData.SANJIGI, question.getId()));

        // then
    }
}
