package qna.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import qna.CannotDeleteException;
import qna.domain.Answer;
import qna.domain.DeleteHistories;
import qna.domain.DeleteHistoryRepository;
import qna.domain.Question;
import qna.domain.QuestionTest;
import qna.domain.UserTest;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DeleteHistoryServiceTest {
    @Mock
    private DeleteHistoryRepository deleteHistoryRepository;

    @InjectMocks
    private DeleteHistoryService deleteHistoryService;

    private Question question;

    @Before
    public void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    public void saveAllTest() throws CannotDeleteException {
        DeleteHistories deleteHistories = question.delete(UserTest.JAVAJIGI);
        deleteHistoryService.saveAll(deleteHistories);

        verify(deleteHistoryRepository).saveAll(deleteHistories);
    }
}
