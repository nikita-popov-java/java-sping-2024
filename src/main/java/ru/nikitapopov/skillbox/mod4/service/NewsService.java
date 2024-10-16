package ru.nikitapopov.skillbox.mod4.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.nikitapopov.skillbox.mod4.dto.NewsDTO;
import ru.nikitapopov.skillbox.mod4.mapper.NewsMapper;
import ru.nikitapopov.skillbox.mod4.model.News;
import ru.nikitapopov.skillbox.mod4.repository.NewsRepository;

//@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    public Page<NewsDTO> getAllNews(Pageable pageable) {
        return newsRepository.findAll(pageable).map(newsMapper::toDto);
    }

    public NewsDTO getNewsById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));
        return newsMapper.toDto(news);
    }

    public Page<NewsDTO> filterNews(String category, String author, Pageable pageable) {
        return (Page<NewsDTO>) newsRepository.findAll(pageable)
                .map(newsMapper::toDto)
                .filter(n -> n.getAuthor().equalsIgnoreCase(author) && n.getCategory().equalsIgnoreCase(category));
    }

    public NewsDTO createNews(NewsDTO newsDTO) {
        News news = newsMapper.toEntity(newsDTO);
        news = newsRepository.save(news);
        return newsMapper.toDto(news);
    }

    public NewsDTO updateNews(Long id, NewsDTO newsDTO) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("News not found"));
        news.setTitle(newsDTO.getTitle());
        news.setContent(newsDTO.getContent());
        news.setCategory(news.getCategory()); // Обновление категории
        newsRepository.save(news);
        return newsMapper.toDto(news);
    }

    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }
}
