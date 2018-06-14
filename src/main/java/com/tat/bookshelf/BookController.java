package com.tat.bookshelf;

import com.tat.bookshelf.domain.Book;
import com.tat.bookshelf.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    private List<Book> books = new ArrayList<>();
    private int currentPage = 1;

    @Autowired
    private BookRepo bookRepo;

    @GetMapping
    public String main(Map<String, Object> model) {
        books = (List) bookRepo.findAll();

        model.put("pages", initializePages(books));
        model.put("books", booksOnPage(1));
        return "main";
    }

    @PostMapping("/{{value}}")
    public String anotherPage(@RequestParam String value,
                              Map<String, Object> model) {
        currentPage = Integer.parseInt(value);
        model.put("pages", initializePages(books));
        model.put("books", booksOnPage(Integer.parseInt(value)));
        return "main";
    }

    @PostMapping("/add")
    public String add(@RequestParam String title,
                      @RequestParam String description,
                      @RequestParam String author,
                      @RequestParam String isbn,
                      @RequestParam String printYear,
                      Map<String, Object> model) {
        if (!title.isEmpty() && !description.isEmpty() && !author.isEmpty()
                && !isbn.isEmpty() && !printYear.isEmpty()) {
            try {
                int py = Integer.parseInt(printYear);
                Book book = new Book(title, description, author, isbn, py, false);
                bookRepo.save(book);
            } catch (NumberFormatException e) {

            }
        }
        currentPage = 1;
        books = (List) bookRepo.findAll();
        model.put("pages", initializePages(books));
        model.put("books", booksOnPage(currentPage));
        return "main";
    }

    @PostMapping("filterTitle")
    public String filterTitle(@RequestParam String filter, Map<String, Object> model) {
        if (filter != null && !filter.isEmpty()) {
            books = bookRepo.findByTitle(filter);
        } else {
            books = (List) bookRepo.findAll();
        }
        currentPage = 1;
        model.put("pages", initializePages(books));
        model.put("books", booksOnPage(currentPage));
        return "main";
    }

    @PostMapping("filterAuthor")
    public String filterAuthor(@RequestParam String filter, Map<String, Object> model) {
        if (filter != null && !filter.isEmpty()) {
            books = bookRepo.findByAuthor(filter);
        } else {
            books = (List) bookRepo.findAll();
        }
        currentPage = 1;
        model.put("pages", initializePages(books));
        model.put("books", booksOnPage(currentPage));
        return "main";
    }

    @PostMapping("filterNotRead")
    public String filterNotRead(Map<String, Object> model) {
        books = bookRepo.findByReadAlready(false);
        if (books.isEmpty()) {
            books = (List) bookRepo.findAll();
        }
        currentPage = 1;
        model.put("pages", initializePages(books));
        model.put("books", booksOnPage(currentPage));
        return "main";
    }

    @PostMapping("filterYear")
    public String filterYear(@RequestParam String filterFrom, @RequestParam String filterTo, Map<String, Object> model) {
        int from, to;
        try {
            from = Integer.parseInt(filterFrom);
        } catch (NumberFormatException e) {
            from = 0;
        }
        try {
            to = Integer.parseInt(filterTo);
        } catch (NumberFormatException e) {
            to = 0;
        }

        List<Book> booksFrom;
        if (from != 0) {
            booksFrom = bookRepo.findByPrintYearAfter(from);
        } else {
            booksFrom = (List<Book>) bookRepo.findAll();
        }

        List<Book> booksTo;
        if (to != 0) {
            booksTo = bookRepo.findByPrintYearBefore(to);
        } else {
            booksTo = (List<Book>) bookRepo.findAll();
        }

        books = new ArrayList<>();
        if (booksFrom.containsAll(booksTo) && booksTo.containsAll(booksFrom)) {
            books = booksFrom;
        } else {
            for(Book book : booksFrom) {
                if (booksTo.contains(book)) {
                    books.add(book);
                }
            }
        }

        currentPage = 1;
        model.put("pages", initializePages(books));
        model.put("books", booksOnPage(currentPage));
        return "main";
    }

    @PostMapping("allBooks")
    public String allBooks(Map<String, Object> model) {
        return main(model);
    }

    @PostMapping("read")
    public String read(@RequestParam String id, Map<String, Object> model) {
        long lId = Long.parseLong(id);
        Book book = bookRepo.findById(lId).get();

        if (!book.isReadAlready()) {
            book.setReadAlready(true);
            bookRepo.save(book);

            int toUpdate = indexOfBook(lId);
            books.remove(toUpdate);
            books.add(toUpdate, book);
        }

        model.put("pages", initializePages(books));
        model.put("books", booksOnPage(currentPage));
        return "main";
    }

    @PostMapping("/update")
    public String update(@RequestParam String id, Map<String, Object> model) {
        long lId = Long.parseLong(id);
        Book book = bookRepo.findById(lId).get();
        model.put("book", book);
        return "update";
    }

    @PostMapping("/startUpdate")
    public String startUpdate(@RequestParam String id,
                              @RequestParam String title,
                              @RequestParam String description,
                              @RequestParam String isbn,
                              @RequestParam String printYear,
                              Map<String, Object> model) {
        long lId = Long.parseLong(id);
        Book book = bookRepo.findById(lId).get();

        if (title != null && !title.isEmpty()) {
            book.setTitle(title);
        }

        if (description != null && !description.isEmpty()) {
            book.setDescription(description);
        }

        if (isbn != null && !isbn.isEmpty()) {
            book.setIsbn(isbn);
        }

        if (printYear != null && !printYear.isEmpty()) {
            try {
                int py = Integer.parseInt(printYear);
                book.setPrintYear(py);
            } catch (NumberFormatException e) {

            }
        }

        book.setReadAlready(false);
        bookRepo.save(book);

        books = (List) bookRepo.findAll();
        model.put("pages", initializePages(books));
        model.put("books", booksOnPage(1));
        return "main";
    }

    @PostMapping("delete")
    public String delete(@RequestParam String id, Map<String, Object> model) {
        long lId = Long.parseLong(id);
        if (bookRepo.existsById(lId)) {
            bookRepo.deleteById(lId);

            int toUpdate = indexOfBook(lId);
            books.remove(toUpdate);
        }
        model.put("pages", initializePages(books));
        model.put("books", booksOnPage(currentPage));
        return "main";
    }

    private List<Integer> initializePages(List<Book> books) {
        int pageCount;
        if (books.size() % 10 == 0) {
            pageCount = books.size() / 10;
        } else {
            pageCount = books.size() / 10 + 1;
        }
        List<Integer> pages = new ArrayList<>();
        for (int i = 1; i <= pageCount; i++) {
            pages.add(i);
        }

        if (currentPage > pageCount) {
            currentPage = pageCount;
        }

        return pages;
    }

    private List<Book> booksOnPage(int p) {
        int from = (p - 1) * 10;
        int to = from + 10;
        if (to > books.size()) {
            to = books.size();
        }
        return books.subList(from, to);
    }

    private int indexOfBook(long id) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
}
