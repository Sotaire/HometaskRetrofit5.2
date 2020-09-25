package kg.geektech.courses.android3.lesson1sept.ui.main;

import kg.geektech.courses.android3.lesson1sept.data.TestRepo;
import kg.geektech.courses.android3.lesson1sept.data.models.Book;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private MainContract.Model model;

    public MainPresenter(MainContract.View mView) {
        this.mView = mView;
        model = new MainModel();
    }

    @Override
    public void bookOnClick() {
        mView.showBook(loadBook());
    }

    @Override
    public Book loadBook() {
        return model.getBookById(3);
    }
}
