package com.example.books4all;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "item")
public class CardItem {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "item_id")
    private int id;

    @ColumnInfo(name = "item_image")
    private String imageResource;

    @ColumnInfo(name = "item_name")
    private String bookName;

    @ColumnInfo(name = "item_summary")
    private String bookSummary;

    @ColumnInfo(name = "item_page")
    private Integer bookPage;

    @ColumnInfo(name = "item_type")
    private String bookType;

    @ColumnInfo(name = "item_lang")
    private String bookLang;

    public CardItem(String imageResource, String bookName, String bookSummary, String bookType,Integer bookPage, String bookLang) {
        this.imageResource = imageResource;
        this.bookName = bookName;
        this.bookSummary = bookSummary;
        this.bookPage = bookPage;
        this.bookType = bookType;
        this.bookLang = bookLang;
        //this.date = date;
    }

    public String getImageResource() {
        return imageResource;
    }

    public String getBookName() {
        return bookName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookSummary() {
        return bookSummary;
    }

    public void setBookSummary(String bookSummary) {
        this.bookSummary = bookSummary;
    }

    public String getBookType() {
        return bookType;
    }

    public String getBookLang() {
        return bookLang;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public Integer getBookPage() {
        return bookPage;
    }

    public void setBookPage(Integer bookPage) {
        this.bookPage = bookPage;
    }

    /*public String getDate() {
        return date;
    }*/
}
