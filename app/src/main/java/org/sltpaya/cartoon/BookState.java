package org.sltpaya.cartoon;

/**
 * Author: SLTPAYA
 * Date: 2017/3/9
 */
public class BookState {

    private String bookId;
    private String showViewType;
    private String bookName;
    private String groupTitle;
    private String typeId;
    private String classType;

    public BookState(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getShowViewType() {
        return showViewType;
    }

    public void setShowViewType(String showViewType) {
        this.showViewType = showViewType;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getGroupTitle() {
        return groupTitle;
    }

    public void setGroupTitle(String groupTitle) {
        this.groupTitle = groupTitle;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }
}
