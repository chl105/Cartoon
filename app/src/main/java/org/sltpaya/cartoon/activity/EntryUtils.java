package org.sltpaya.cartoon.activity;

import android.content.Context;
import android.content.Intent;

/**
 * Author: SLTPAYA
 * Date: 2017/3/11
 * 本工具类作用：
 * 进入相应的Activity
 */
public class EntryUtils {

    public static final String CARTOON = "cartoon";
    public static final String NOVEL = "novel";

    public static void entry(Item item, Context context) {
        String bookId = item.getBookId();
        String classType = item.getClassType();
        if (CARTOON.equals(classType)) {
            String type = item.getViewType();
            if ("1".equals(type)) {
                entryStripManDetail(context, bookId);
            } else if ("0".equals(type)) {
                entryBookNewDetails(context, bookId);
            } else {
                String title = item.getBookName();
                String typeId = item.getTypeId();
                entryMoreActivity(context, title, typeId);
            }
        } else {
            entryNovelDetailActivity(context, bookId);
        }
    }

    private static void entryActivity(Class cls ,Context context, String bookId) {
        Intent intent = new Intent(context, cls);
        intent.putExtra("bookid", bookId);
        context.startActivity(intent);
    }

    private static void entryStripManDetail(Context context, String bookid) {
       entryActivity(StripManDetailActivity.class, context, bookid);
    }

    private static void entryBookNewDetails(Context context, String bookid) {
        entryActivity(BookNewDetailsActivity.class, context, bookid);
    }

    private static void entryNovelDetailActivity(Context context, String bookid) {
        entryActivity(NovelDetailActivity.class, context, bookid);
    }

    //0为标题名，1位类型（cartoon/novel),2为type的id
    private static void entryMoreActivity(Context context, String title, String typeId) {
        String[] info = {title, "cartoon", typeId};
        Intent intent = new Intent(context, BookMoreActivity.class);
        intent.putExtra("info", info);
        context.startActivity(intent);
    }

    public static class Item {

        private String classType;
        private String bookId;
        private String viewType;
        private String bookName;
        private String typeId;

        /**
         * 进入Activity
         *
         * @param classType cartoon或者novel模块
         * @param bookId    book id
         * @param viewType  针对漫画Activity的跳转，如果是novel activity跳转，请传-1
         */
        public Item(String classType, String bookId, String viewType) {
            this.classType = classType;
            this.bookId = bookId;
            this.viewType = viewType;
        }

        public String getClassType() {
            return classType;
        }

        public String getBookId() {
            return bookId;
        }

        public String getViewType() {
            return viewType;
        }

        public String getBookName() {
            return bookName;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }
    }


}
