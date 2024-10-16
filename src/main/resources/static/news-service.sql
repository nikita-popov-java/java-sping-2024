-- Создание таблицы "User"
CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email    VARCHAR(100) NOT NULL UNIQUE
);

-- Создание таблицы "Category"
CREATE TABLE categories
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

-- Создание таблицы "News"
CREATE TABLE news
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    content     TEXT         NOT NULL,
    author_id   INT          NOT NULL,
    category_id INT          NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE
);

-- Создание таблицы "Comment"
CREATE TABLE comments
(
    id        SERIAL PRIMARY KEY,
    content   TEXT NOT NULL,
    author_id INT  NOT NULL,
    news_id   INT  NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (news_id) REFERENCES news (id) ON DELETE CASCADE
);