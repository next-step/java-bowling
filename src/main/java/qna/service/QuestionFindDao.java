package qna.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qna.NotFoundException;
import qna.domain.Question;
import qna.domain.QuestionRepository;

@Service
@Transactional
public class QuestionFindDao {

    private final QuestionRepository questionRepository;

    public QuestionFindDao(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question findById(Long id) {
        return questionRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(NotFoundException::new);
    }

}
