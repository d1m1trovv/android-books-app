package com.example.booksapplication.Services;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.booksapplication.Models.UserModel;
import com.example.booksapplication.Models.BookModel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.graphics.BitmapFactory.*;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 22;
    private static final String DATABASE_NAME = "dbManager";
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_BOOKS = "Books";
    private static final String KEY_USER_ID = "id";
    private static final String KEY_USER_NAME = "name";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_BOOK_ID = "bookId";
    private static final String KEY_IMAGE_PATH = "image";
    private static final String KEY_BOOK_NAME = "bookName";
    private static final String KEY_PAGES_COUNT = "pagesCount";
    private static final String KEY_GENRE = "genre";
    private static final String KEY_IS_FAVORITE = "isFavorite";
    private static final String KEY_USER_ISLOGGED = "isLogged";
    private List<BookModel> favoriteBooks = new ArrayList<>();
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + KEY_USER_ID + " INTEGER PRIMARY KEY," + KEY_USER_NAME + " TEXT,"
                + KEY_PASSWORD + " TEXT," +  KEY_USER_ISLOGGED + " TEXT" + ")";
        String CREATE_BOOKS_TABLE = "CREATE TABLE " + TABLE_BOOKS + "("
                + KEY_BOOK_ID + " INTEGER PRIMARY KEY," + KEY_IMAGE_PATH + " INTEGER," + KEY_BOOK_NAME + " TEXT,"
                + KEY_PAGES_COUNT + " INTEGER," + KEY_GENRE + " TEXT," + KEY_IS_FAVORITE + " TEXT" + ")";
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_BOOKS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(db);
    }
    public void addUserName(UserModel usModel) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_NAME, usModel.get_name());
        values.put(KEY_PASSWORD, usModel.get_password());

        db.insert(TABLE_USERS, null, values);

        db.close(); // Closing database connection
    }

    public void addBook(BookModel bookModel) {

        /*Bitmap storedBitmap = null;
        String sql = "INSERT INTO " + TABLE_BOOKS + " VALUES(NULL,?,?,?,?)";
        SQLiteDatabase db = this.getWritableDatabase();
        SQLiteStatement insertStmt = db.compileStatement(sql);

        //byte[] imgByte = getBitmapAsByteArray(bookModel.get_image());

        insertStmt.bindString(1, bookModel.get_image());
        insertStmt.bindString(2, bookModel.get_bookName());
        insertStmt.bindString(3, bookModel.get_pagesCount());
        insertStmt.bindString(4, bookModel.get_genre());
        insertStmt.executeInsert();
        db.close();*/

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IMAGE_PATH, bookModel.get_image());
        values.put(KEY_BOOK_NAME, bookModel.get_bookName());
        values.put(KEY_PAGES_COUNT, bookModel.get_pagesCount());
        values.put(KEY_GENRE, bookModel.get_genre());
        values.put(KEY_IS_FAVORITE, bookModel.isFavorite());

        db.insert(TABLE_BOOKS, null, values);

        db.close(); // Closing database connection
    }

    private byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 0, outputStream);
        return outputStream.toByteArray();
    }


    public void makeBookFavorite(int id, String isFavorite){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_IS_FAVORITE, isFavorite);
        db.update(TABLE_BOOKS, values, KEY_BOOK_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<BookModel> getAllBooks(){
        List<BookModel> books = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_BOOKS;

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BookModel bookModel = new BookModel();
                bookModel.set_id(Integer.parseInt(cursor.getString(0)));
                bookModel.set_image(Integer.parseInt(cursor.getString(1)));
                bookModel.set_bookName(cursor.getString(2));
                bookModel.set_pagesCount(cursor.getString(3));
                bookModel.set_genre(cursor.getString(4));
                bookModel.setFavorite(cursor.getString(5));

                books.add(bookModel);
            } while (cursor.moveToNext());
        }

        db.close();
        return books;
    }

    public BookModel getBookById(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BOOKS, new String[] { KEY_BOOK_ID,
                        KEY_IMAGE_PATH, KEY_BOOK_NAME, KEY_PAGES_COUNT, KEY_GENRE, KEY_IS_FAVORITE }, KEY_BOOK_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        BookModel book = new BookModel(Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)) ,cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return book;
    }

    public void isUserLogged(String userName, String logged){
        SQLiteDatabase db = this.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER_ISLOGGED, logged);
        db.update(TABLE_USERS, values, KEY_USER_NAME + " = ?", new String[]{userName});
        db.close();
    }

    public List<UserModel> getAllUsers() {
        List<UserModel> userList = new ArrayList<UserModel>();

        String selectQuery = "SELECT * FROM " + TABLE_USERS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                UserModel umodel = new UserModel();
                umodel.set_id(Integer.parseInt(cursor.getString(0)));
                umodel.set_name(cursor.getString(1));
                umodel.set_password(cursor.getString(2));
                umodel.setIsLogged(cursor.getString(3));

                userList.add(umodel);
            } while (cursor.moveToNext());
        }


        return userList;
    }

    public UserModel getUserByID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_USER_ID,
                        KEY_USER_NAME, KEY_PASSWORD }, KEY_USER_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        UserModel users = new UserModel(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return users;
    }
    public boolean getUser(String userName, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + KEY_USER_NAME + " LIKE '%" + userName+ "%' and "
                + KEY_PASSWORD + " LIKE '%" + password+ "%'"  ;
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.getCount() > 0){
            return true;
        } else {
            return false;
        }
    }

}

