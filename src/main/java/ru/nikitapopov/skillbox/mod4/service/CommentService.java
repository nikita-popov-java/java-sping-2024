package ru.nikitapopov.skillbox.mod4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nikitapopov.skillbox.mod4.dto.CommentDTO;
import ru.nikitapopov.skillbox.mod4.mapper.CommentMapper;
import ru.nikitapopov.skillbox.mod4.repository.CommentRepository;
import ru.nikitapopov.skillbox.mod4.repository.NewsRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final CommentMapper commentMapper;


    public List<CommentDTO> getCommentsByNewsId(Long newsId) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getNews().getId().equals(newsId))
                .map(commentMapper::toDto)
                .toList();
    }

    // Создать комментарий для новости
    public CommentDTO createComment(Long newsId, CommentDTO commentDTO) {
        var news = newsRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));

        var comment = commentMapper.toEntity(commentDTO);
        comment.setNews(news);
        comment = commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    // Обновить комментарий
    public CommentDTO updateComment(Long id, CommentDTO commentDTO) {
        var comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.setContent(commentDTO.getContent());
        commentRepository.save(comment);
        return commentMapper.toDto(comment);
    }

    // Удалить комментарий
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
