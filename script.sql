USE test;

DROP TABLE IF EXISTS book;

CREATE TABLE book (id INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
title VARCHAR(100),
description VARCHAR(255),
author VARCHAR(100),
isbn varchar(20), 
print_year INT, 
read_already TINYINT(1))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

INSERT INTO book (id, title, description, author, isbn, print_year, read_already) VALUES 
(NULL, "Spring Persistence with Hibernate", "Guides you through the essential aspects and best practices of building a real application, using Spring Framework 3, Hibernate 3.5, Grails, and Roo", "Paul Tepper Fisher and Brian D. Murphy", "978-1-4302-2632-1", 2010, 0), 
(NULL, "Harry Potter and the Philosopher's Stone", "Harry Potter and the Philosopher’s Stone is the first novel of the much appreciated Harry Potter series.", "J.K. Rowling", "0-7475-3269-9", 1997, 0),
(NULL, "Harry Potter and the Chamber of Secrets", "Harry Potter and the Chamber of Secrets is the second novel in the Harry Potter series.", "J.K. Rowling", "0-7475-3849-2", 1998, 0),
(NULL, "Harry Potter and the Prisoner of Azkaban", "Harry Potter and the Prisoner of Azkaban is the third in the Harry Potter series.", "J.K. Rowling", "0-7475-4215-5", 1999, 0),
(NULL, "Harry Potter and the Goblet of Fire", "Harry Potter and the Goblet of Fire is the fourth novel in the Harry Potter series.", "J.K. Rowling", "0-7475-4624-X", 2000, 0),
(NULL, "Harry Potter and the Order of the Phoenix", "Harry Potter and the Order of the Phoenix is the fifth novel in the Harry Potter series.", "J.K. Rowling", "0-7475-5100-6", 2003, 0),
(NULL, "Harry Potter and the Half-Blood Prince", "Harry Potter and the Half-Blood Prince is the sixth and penultimate novel in the Harry Potter series.", "J.K. Rowling", "0-7475-8108-8", 2005, 0),
(NULL, "Harry Potter and the Deathly Hallows", "Harry Potter and the Deathly Hallows is the seventh and final novel of the Harry Potter series.", "J.K. Rowling", "0-545-01022-5", 2007, 0),
(NULL, "A Game of Thrones", "A Game of Thrones is the first novel in A Song of Ice and Fire.", "George R. R. Martin", "0-553-10354-7", 1996, 0),
(NULL, "A Clash of Kings", "A Clash of Kings is the second novel in A Song of Ice and Fire.", "George R. R. Martin", "0-00-224585-X", 1998, 0),
(NULL, "A Storm of Swords", "A Storm of Swords is the third of seven planned novels in A Song of Ice and Fire.", "George R. R. Martin", "0-553-10663-5", 2000, 0),
(NULL, "A Feast for Crows", "A Feast for Crows is the fourth of seven planned novels in the epic fantasy series A Song of Ice and Fire.", "George R. R. Martin", "0-00-224743-7", 2005, 0),
(NULL, "A Dance with Dragons", "A Dance with Dragons is the fifth of seven planned novels in the epic fantasy series A Song of Ice and Fire.", "George R. R. Martin", "978-0553801477", 2011, 0),
(NULL, "The Hobbit", "The Hobbit, or There and Back Again is a children's fantasy novel.", "J. R. R. Tolkien", "9780582186552", 1937, 0),
(NULL, "The Lord of the Rings", "The Lord of the Rings is an epic high fantasy novel.", "J. R. R. Tolkien", "9780618640157", 1954, 0),
(NULL, "The Silmarillion", "The Silmarillion is a collection of mythopoeic works.", "J. R. R. Tolkien", "0-04-823139-8", 1977, 0),
(NULL, "Digital Fortress", "Digital Fortress is a techno-thriller novel.", "Dan Brown", "0-312-18087-X", 1998, 0),
(NULL, "Angels and Demons", "Angels and Demons is a 2000 bestselling mystery-thriller novel.", "Dan Brown", "0-671-02735-2", 2000, 0),
(NULL, "The Da Vinci Code", "The Da Vinci Code is a 2003 mystery thriller novel.", "Dan Brown", "0-385-50420-9", 2003, 0),
(NULL, "The Lost Symbol", "The Lost Symbol is a 2009 novel.", "Dan Brown", "978-0-385-50422-5", 2009, 0),
(NULL, "Inferno", "Inferno is a 2013 mystery thriller novel.", "Dan Brown", "978-0-385-53785-8", 2013, 0),
(NULL, "Origin", "Origin is a 2017 mystery thriller novel.", "Dan Brown", "978-0-593-07875-4", 2017, 0),
(NULL, "The Last Wish", "The Last Wish is the first (in its fictional chronology; published second in original Polish) of the two collections of short stories (the other being The Sword of Destiny) preceding the main Witcher Saga.", "Andrzej Sapkowski", "978-0-575-08244-1", 1993, 0),
(NULL, "Sword of Destiny", "Sword of Destiny is the second (in its fictional chronology; first in Polish print) of the two collections of short stories (the other being The Last Wish), both preceding the main Witcher Saga.", "Andrzej Sapkowski", "978-1-4732-1153-7", 1992, 0),
(NULL, "Blood of Elves", "Blood of Elves is the first novel in Witcher Saga", "Andrzej Sapkowski", "978-0-575-08484-1", 1994, 0),
(NULL, "Time of Contempt", "Time of Contempt is the second novel in the Witcher Saga.", "Andrzej Sapkowski", "978-0-575-09094-1", 1995, 0),
(NULL, "Baptism of Fire", "Baptism of Fire is the third novel in the Witcher Saga.", "Andrzej Sapkowski", "978-0-575-09097-2", 1996, 0),
(NULL, "The Tower of the Swallow", "The Tower of the Swallow is the fourth novel in the Witcher Saga.", "Andrzej Sapkowski", "5-17-016209-X", 1997, 0);


