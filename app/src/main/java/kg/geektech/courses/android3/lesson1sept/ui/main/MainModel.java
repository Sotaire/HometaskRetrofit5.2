package kg.geektech.courses.android3.lesson1sept.ui.main;

import kg.geektech.courses.android3.lesson1sept.data.TestRepo;
import kg.geektech.courses.android3.lesson1sept.data.models.Book;

public class MainModel implements MainContract.Model {


    @Override
    public Book getBookById(int id) {
        return TestRepo.getBookById(id);
    }
}
