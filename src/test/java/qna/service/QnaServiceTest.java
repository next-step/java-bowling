package qna.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qna.CannotDeleteException;
import qna.domain.*;

import java.time.LocalDateTime;
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

    @BeforeEach
    public void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @DisplayName("본인이 작성한 잘문에 답변이 없는 경우, 본인이 작성한 질문을 삭제할 때, 질문은 삭제된 상태가 되어야 하고, 삭제 이력이 저장되어야 한다.")
    @Test
    public void delete_givenOwnedQuestion() {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        verify(deleteHistoryService).saveAll(List.of(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now())));
    }

    @DisplayName("본인이 작성하지 않은 질문을 삭제할 때, 예외가 발생해야 한다.")
    @Test
    public void delete_givenNotOwnedQuestion() {
        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.SANJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("본인이 작성한 잘문에 달려있는 답변을 모두 본인이 작성하였다면, 본인이 작성한 질문을 삭제할 때, 질문과 질문에 달려있는 답변은 삭제된 상태가 되어야 하고, 삭제 이력이 저장되어야 한다.")
    @Test
    public void delete_givenOwnedQuestionAndOwnedAllAnswers() {
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);

        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
        verify(deleteHistoryService).saveAll(
                List.of(
                        new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now())
                )
        );
    }

    @DisplayName("본인이 작성한 질문에 본인이 작성하지 않은 답변을 포함하고 있을 때, 질문을 삭제하면 예외가 발생해야 한다.")
    @Test
    public void delete_whenContainingNotOwnedAnswer() {
        Answer answer1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer1);
        Answer answer2 = new Answer(12L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        question.addAnswer(answer2);

        when(questionRepository.findByIdAndDeletedFalse(question.getId())).thenReturn(Optional.of(question));

        assertThatThrownBy(() -> {
            qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());
        }).isInstanceOf(CannotDeleteException.class);
    }


}
