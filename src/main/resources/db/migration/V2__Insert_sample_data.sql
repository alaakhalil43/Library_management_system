
-- insert random data for testing Endpoints

-- Insert sample roles
INSERT INTO roles (name) VALUES
('ADMINISTRATOR'),
('LIBRARIAN'),
('STAFF'),
('MEMBER');

-- Insert sample users with one-to-one role relationship and addresses
-- Password for all users: admin123
INSERT INTO users (username, email, password, first_name, last_name, address, role_id) VALUES
('admin', 'admin@library.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'Admin', 'User', '123 Admin Street, Library City, LC 12345', 1),
('librarian1', 'librarian1@library.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'Jane', 'Smith', '456 Librarian Ave, Book Town, BT 67890', 2),
('staff1', 'staff1@library.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'John', 'Doe', '789 Staff Road, Reading City, RC 13579', 3),
('superuser', 'superuser@library.com', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'Super', 'User', '321 Super Lane, Manager Town, MT 24680', 2);

-- Insert sample authors with biographies
INSERT INTO authors (first_name, last_name, biography) VALUES
('J.K.', 'Rowling', 'British author best known for writing the Harry Potter fantasy series. The books have won multiple awards and sold more than 500 million copies worldwide.'),
('George', 'Orwell', 'English novelist and essayist, journalist and critic. His work is characterised by lucid prose, biting social criticism, and outspoken support of democratic socialism.'),
('Isaac', 'Asimov', 'American writer and professor of biochemistry at Boston University. He was known for his works of science fiction and popular science.'),
('Stephen', 'King', 'American author of horror, supernatural fiction, suspense, crime, science-fiction, and fantasy novels. He has published 64 novels and 200 short stories.'),
('Agatha', 'Christie', 'English writer known for her sixty-six detective novels and fourteen short story collections, particularly those revolving around fictional detectives Hercule Poirot and Miss Marple.'),
('Robert C.', 'Martin', 'American software engineer and instructor. He is best known for being one of the authors of the Agile Manifesto and for developing several software design principles.'),
('Martin', 'Fowler', 'British software developer, author and international public speaker on software development, specialising in object-oriented analysis and and design, UML, patterns, and agile software development methodologies.'),
('Eric', 'Evans', 'Software engineer and author. He is the author of Domain-Driven Design: Tackling Complexity in the Heart of Software, which describes the discipline of domain-driven design.');

-- Insert sample categories with hierarchical structure
INSERT INTO categories (name, description, parent_id) VALUES
-- Root categories
('Fiction', 'Fictional literature including novels and stories', NULL),
('Non-Fiction', 'Non-fictional literature including biographies and history', NULL),
('Science & Technology', 'Scientific and technological literature', NULL),
-- Fiction subcategories
('Fantasy', 'Fantasy novels and magical stories', 1),
('Mystery', 'Mystery and detective fiction', 1),
('Romance', 'Romance novels', 1),
('Horror', 'Horror and supernatural fiction', 1),
-- Non-Fiction subcategories
('Biography', 'Biographical and autobiographical works', 2),
('History', 'Historical literature and research', 2),
-- Science & Technology subcategories
('Computer Science', 'Programming and software development books', 3),
('Engineering', 'Engineering and technical manuals', 3);

-- Insert sample publishers
INSERT INTO publishers (name, description) VALUES
('Penguin Random House', 'One of the largest publishing companies'),
('HarperCollins', 'Major publishing company'),
('O''Reilly Media', 'Technology and programming books'),
('MIT Press', 'Academic and scientific publications'),
('Oxford University Press', 'Academic and reference books');

-- Insert sample languages
INSERT INTO languages (name) VALUES
('English'),
('Spanish'),
('French'),
('German'),
('Italian'),
('Portuguese'),
('Russian'),
('Chinese'),
('Japanese'),
('Arabic');

-- Insert sample books with summary and updated category IDs
INSERT INTO books (title, isbn, publication_year, language_id, edition, summary, cover_image_url, total_copies, available_copies, category_id, publisher_id) VALUES
('Harry Potter and the Philosopher''s Stone', '978-0747532699', 1997, 1, '1st Edition', 'A young wizard discovers his magical heritage and attends Hogwarts School of Witchcraft and Wizardry, where he learns about friendship, courage, and the power of love while facing the dark wizard who killed his parents.', 'https://example.com/covers/harry-potter-1.jpg', 5, 5, 4, 1), -- Fantasy
('1984', '978-0451524935', 1949, 1, '1st Edition', 'A dystopian social science fiction novel about totalitarian control and surveillance in a society where individual freedom is completely eliminated and reality is manipulated by the Party.', 'https://example.com/covers/1984.jpg', 3, 3, 1, 1), -- Fiction
('Foundation', '978-0553293357', 1951, 1, '1st Edition', 'The first book in the Foundation series, a science fiction epic about the fall and rise of galactic civilization, following mathematician Hari Seldon as he develops psychohistory to predict the future.', 'https://example.com/covers/foundation.jpg', 2, 2, 1, 1), -- Fiction
('The Shining', '978-0307743657', 1977, 1, '1st Edition', 'A psychological horror novel about a family who becomes the caretakers of an isolated hotel with a dark past, where supernatural forces begin to drive the father toward violence.', 'https://example.com/covers/shining.jpg', 4, 4, 7, 2), -- Horror
('Murder on the Orient Express', '978-0062693662', 1934, 1, '1st Edition', 'A classic mystery novel featuring detective Hercule Poirot investigating a murder aboard the luxurious Orient Express train, where every passenger becomes a suspect.', 'https://example.com/covers/orient-express.jpg', 3, 3, 5, 2), -- Mystery
('Clean Code', '978-0132350884', 2008, 1, '1st Edition', 'A handbook of agile software craftsmanship focusing on writing clean, maintainable code with practical examples and principles for improving code quality and readability.', 'https://example.com/covers/clean-code.jpg', 2, 2, 10, 3), -- Computer Science
('Refactoring', '978-0134757599', 2018, 1, '2nd Edition', 'A comprehensive guide to improving the design of existing code through refactoring techniques, helping developers maintain and enhance software systems without changing their behavior.', 'https://example.com/covers/refactoring.jpg', 2, 2, 10, 3), -- Computer Science
('Domain-Driven Design', '978-0321125217', 2003, 1, '1st Edition', 'A comprehensive guide to domain-driven design principles and practices for software development, focusing on creating software that reflects real-world business domains.', 'https://example.com/covers/ddd.jpg', 1, 1, 10, 4); -- Computer Science

-- Insert book-author relationships (many-to-many)
INSERT INTO book_authors (book_id, author_id) VALUES
(1, 1), -- Harry Potter - J.K. Rowling
(2, 2), -- 1984 - George Orwell
(3, 3), -- Foundation - Isaac Asimov
(4, 4), -- The Shining - Stephen King
(5, 5), -- Murder on the Orient Express - Agatha Christie
(6, 6), -- Clean Code - Robert C. Martin
(7, 7), -- Refactoring - Martin Fowler
(8, 8); -- Domain-Driven Design - Eric Evans

-- Insert sample members with addresses
INSERT INTO members (first_name, last_name, email, phone, address) VALUES
('Alice', 'Johnson', 'alice.johnson@email.com', '555-0101', '123 Main Street, New York, NY 10001'),
('Bob', 'Wilson', 'bob.wilson@email.com', '555-0102', '456 Oak Avenue, Los Angeles, CA 90210'),
('Carol', 'Davis', 'carol.davis@email.com', '555-0103', '789 Pine Road, Chicago, IL 60601'),
('David', 'Brown', 'david.brown@email.com', '555-0104', '321 Elm Street, Houston, TX 77001'),
('Eva', 'Miller', 'eva.miller@email.com', '555-0105', '654 Maple Lane, Phoenix, AZ 85001');



-- Insert sample activity logs
INSERT INTO user_activity_logs (user_id, action, timestamp) VALUES
(1, 'User logged in to the system', NOW() - INTERVAL 2 HOUR),
(2, 'Added new book: Harry Potter and the Philosopher''s Stone', NOW() - INTERVAL 1 HOUR),
(2, 'Updated book details: Harry Potter and the Philosopher''s Stone', NOW() - INTERVAL 45 MINUTE),
(3, 'Added new member: Alice Johnson', NOW() - INTERVAL 30 MINUTE),
(1, 'Borrowed book: Harry Potter and the Philosopher''s Stone by Alice Johnson', NOW() - INTERVAL 15 MINUTE),
(2, 'Searched for books with term: "Harry Potter"', NOW() - INTERVAL 10 MINUTE),
(3, 'Viewed member details: Alice Johnson', NOW() - INTERVAL 5 MINUTE),
(1, 'Deleted book: 1984', NOW() - INTERVAL 2 MINUTE),
(1, 'System maintenance completed', NOW() - INTERVAL 1 MINUTE);
