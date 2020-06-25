package qna.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import qna.domain.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DeleteHistoryServiceTest {

    @Mock
    DeleteHistoryRepository deleteHistoryRepository;

    @InjectMocks
    DeleteHistoryService deleteHistoryService;

    private Question question;
    private Answer answer;

    @Before
    public void setUp() throws Exception {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    public void 게시글을_기록하면_게시글과_댓글_함께_기록된다() {
        deleteHistoryService.save(question);
        verifyDeleteHistories();
    }


    private void verifyDeleteHistories() {
        List<DeleteHistory> deleteHistories = createDeleteHistories();
        verify(deleteHistoryRepository).saveAll(deleteHistories);
    }

    private List<DeleteHistory> createDeleteHistories() {
        return Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
    }

}