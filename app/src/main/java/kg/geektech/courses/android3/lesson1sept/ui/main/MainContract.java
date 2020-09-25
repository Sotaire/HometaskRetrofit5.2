package kg.geektech.courses.android3.lesson1sept.ui.main;

import kg.geektech.courses.android3.lesson1sept.data.models.Book;

public interface MainContract {


    interface View {
        void showBook(Book book);
    }

    interface Presenter {
        void bookOnClick();
        Book loadBook();
    }

    interface Model {
        Book getBookById(int id);
    }
}
