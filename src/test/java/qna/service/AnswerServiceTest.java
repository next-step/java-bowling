package qna.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qna.CannotDeleteException;
import qna.domain.Answer;
import qna.domain.AnswerRepository;
import qna.domain.QuestionTest;
import qna.domain.UserTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AnswerServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    private AnswerService answerService;

    private Answer firstAnswer;
    private Answer deletedFirstAnswer;
    private Answer secondAnswer;

    @BeforeEach
    public void setup() {
        answerService = new AnswerService(answerRepository);
        firstAnswer = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        deletedFirstAnswer = new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        deletedFirstAnswer.setDeleted(true);
        secondAnswer = new Answer(2L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }


    @Test
    @Order(1)
    public void 답변삭제_성공() throws CannotDeleteException {
        when(answerRepository.saveAll(List.of(firstAnswer))).thenReturn(List.of(firstAnswer));
        assertThat(answerService.deleteAll(List.of(firstAnswer), UserTest.JAVAJIGI)).contains(deletedFirstAnswer);
    }

    @Test
    @Order(1)
    public void 답변삭제_실패_다른사람이쓴답변존재() {
        assertThatThrownBy(
                () -> answerService.deleteAll(List.of(secondAnswer), UserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
