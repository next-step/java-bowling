package qna.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import qna.CannotDeleteException;
import qna.NotFoundException;
import qna.domain.*;

@Service
public class QnAService {

    private static final Logger log = LoggerFactory.getLogger(QnAService.class);

    private final QuestionRepository questionRepository;

    private final AnswerRepository answerRepository;

    private final DeleteHistoryService deleteHistoryService;

    public QnAService(QuestionRepository questionRepository,
                      AnswerRepository answerRepository,
                      DeleteHistoryService deleteHistoryService) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.deleteHistoryService = deleteHistoryService;
    }

    @Transactional(readOnly = true)
    public Question findQuestionById(Long id) {
        return questionRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(NotFoundException::new);
    }

    @Transactional
    public void deleteQuestion(User loginUser, long questionId) throws CannotDeleteException {

        Question question = findQuestionById(questionId);

        DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.addDeleteQuestionHistory(question, loginUser);
        deleteHistories.addDeleteAnswersHistories(Answers.ofQuestion(question), loginUser);

        deleteHistoryService.saveAll((List<DeleteHistory>) deleteHistories.getCollection());
    }
}
