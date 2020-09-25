package kg.geektech.courses.android3.lesson1sept.data;

import java.util.ArrayList;
import java.util.List;

import kg.geektech.courses.android3.lesson1sept.data.models.Book;

public class TestRepo {

    public static List<Book> getBookList() {
        //todo implements
        return new ArrayList<>();
    }

    public static Book getBookById(int id) {
        return new Book("book 2", "");
    }

}
