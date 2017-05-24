package com.example.lhg.new_proto;

/**
 * Created by LHG on 2017-05-24.
 */

public class MemoItem {

    //메모 아이템에 필요한 항목들
    //속성이 더 필요하다면, 여기에 추가한 후
    //디비헬퍼, 메모리포에도 양식에 맞춰 추가할 것

    public static final String TABLE = "Memo";

    public static final String KEY_ROWID = "_id";
    public static final String KEY_ID = "id";
    public static final String KEY_type = "type";
    public static final String KEY_memo = "memo";
    public static final String KEY_file = "file";
    public static final String KEY_owner = "owner";

    public int memo_ID;
    public int type;
    public String memo;
    public String file_link;
    public String owner;
}
