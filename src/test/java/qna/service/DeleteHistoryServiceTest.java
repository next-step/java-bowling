package qna.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import qna.domain.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DeleteHistoryServiceTest {

    @Mock
    DeleteHistoryRepository deleteHistoryRepository;

    @InjectMocks
    DeleteHistoryService deleteHistoryService;

    private Question question;
    private Answer answer;

    @BeforeEach
    public void setUp() throws Exception {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @DisplayName("게시글과 댓글의 삭제내역을 기록한다")
    @Test
    public void delete() {
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