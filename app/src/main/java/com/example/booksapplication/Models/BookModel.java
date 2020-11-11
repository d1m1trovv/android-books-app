package com.example.booksapplication.Models;

public class BookModel {
    private int _id;
    private int _image;
    private String isFavorite;
    //private int _image;
    private String _bookName;
    private String _pagesCount;
    private String _genre;

    public BookModel(int _id, int _image, String _bookName, String _pagesCount, String _genre, String isFavorite) {
        this._id = _id;
        this._image = _image;
        this._bookName = _bookName;
        this._pagesCount = _pagesCount;
        this._genre = _genre;
        this.isFavorite = isFavorite;
    }

    public BookModel(int _image, String _bookName, String _pagesCount, String _genre, String isFavorite) {
        this._id = _id;
        this._image = _image;
        this._bookName = _bookName;
        this._pagesCount = _pagesCount;
        this._genre = _genre;
        this.isFavorite = isFavorite;
    }

    public BookModel(){}

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_bookName() {
        return _bookName;
    }

    public void set_bookName(String _bookName) {
        this._bookName = _bookName;
    }

    public String get_pagesCount() {
        return _pagesCount;
    }

    public void set_pagesCount(String _pagesCount) {
        this._pagesCount = _pagesCount;
    }

    public String get_genre() {
        return _genre;
    }

    public void set_genre(String _genre) {
        this._genre = _genre;
    }

    public int get_image() {
        return _image;
    }

    public void set_image(int _image) {
        this._image = _image;
    }

    public String isFavorite() {
        return isFavorite;
    }

    public void setFavorite(String favorite) {
        isFavorite = favorite;
    }

}
