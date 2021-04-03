package qna.domain;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import qna.service.DeleteHistoryService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@Transactional
class DeleteHistoryListTest {

    private Question question;
    private Answer answer;

    private DeleteHistoryService deleteHistoryService;

    @BeforeEach
    public void setUp() throws Exception {
        this.question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        this.answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
        this.deleteHistoryService = new DeleteHistoryService();
    }

    @Test
    void delete_성공(){

        System.out.println(deleteHistoryService);

        assertThat(question.isDeleted()).isFalse();
        question.delete();
        assertThat(question.isDeleted()).isTrue();
//        qnAService.deleteQuestion(UserTest.JAVAJIGI, question.getId());

//        assertThat(question.isDeleted()).isTrue();
//        verifyDeleteHistories();
    }

}
