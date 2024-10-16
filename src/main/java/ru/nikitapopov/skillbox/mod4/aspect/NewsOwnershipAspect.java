package ru.nikitapopov.skillbox.mod4.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import ru.nikitapopov.skillbox.mod4.repository.CommentRepository;
import ru.nikitapopov.skillbox.mod4.repository.NewsRepository;

//@Aspect
//@Component
@RequiredArgsConstructor
public class NewsOwnershipAspect {

    private final NewsRepository newsRepository;
    private final CommentRepository commentRepository;

    @Before("@annotation(ru.nikitapopov.skillbox.mod4.CheckNewsOwnership)")
    public void checkNewsOwnership(JoinPoint point) {
        var requestAttributes = RequestContextHolder.getRequestAttributes();
        var request = ((ServletRequestAttributes) requestAttributes).getRequest();
        var currentUsername = request.getParameter("user");

        System.err.println(currentUsername);

        var news = newsRepository.findById((Long) point.getArgs()[0])
                .orElseThrow(() -> new RuntimeException("News not found"));

        if (!news.getAuthor().getUsername().equals(currentUsername)) {
            throw new RuntimeException("You do not have permission to edit/delete this news.");
        }
    }

    @Before("@annotation(ru.nikitapopov.skillbox.mod4.CheckCommentOwnership)")
    public void checkCommentOwnership(JoinPoint point) {
        var requestAttributes = RequestContextHolder.getRequestAttributes();
        var request = ((ServletRequestAttributes) requestAttributes).getRequest();
        var currentUsername = request.getParameter("user");

        System.err.println(currentUsername);

        var comment = commentRepository.findById((Long) point.getArgs()[0])
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getAuthor().getUsername().equals(currentUsername)) {
            throw new RuntimeException("You do not have permission to edit/delete this comment.");
        }
    }
}

