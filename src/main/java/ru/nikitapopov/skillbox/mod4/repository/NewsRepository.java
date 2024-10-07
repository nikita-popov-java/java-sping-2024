package ru.nikitapopov.skillbox.mod4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.nikitapopov.skillbox.mod4.model.News;

public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News> {
}
