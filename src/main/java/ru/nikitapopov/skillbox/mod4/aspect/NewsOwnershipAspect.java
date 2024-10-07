package ru.nikitapopov.skillbox.mod4.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.nikitapopov.skillbox.mod4.model.News;
import ru.nikitapopov.skillbox.mod4.repository.CommentRepository;
import ru.nikitapopov.skillbox.mod4.repository.NewsRepository;

@Aspect
@Component
@RequiredArgsConstructor
public class NewsOwnershipAspect {

    private final NewsRepository newsRepository;
    private final CommentRepository commentRepository;

    @Before("@annotation(ru.nikitapopov.skillbox.mod4.CheckOwnership) && args(newsId,..)")
    public void checkNewsOwnership(Long newsId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new RuntimeException("News not found"));

        if (!news.getAuthor().getUsername().equals(currentUsername)) {
            throw new RuntimeException("You do not have permission to edit/delete this news.");
        }
    }

    @Before("@annotation(ru.nikitapopov.skillbox.mod4.CheckOwnership) && args(commentId,..)")
    public void checkCommentOwnership(Long commentId) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var currentUsername = authentication.getName();

        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getAuthor().getUsername().equals(currentUsername)) {
            throw new RuntimeException("You do not have permission to edit/delete this comment.");
        }
    }
}

