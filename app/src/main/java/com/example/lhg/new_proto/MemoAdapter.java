package com.example.lhg.new_proto;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LHG on 2017-05-24.
 */

public class MemoAdapter extends CursorAdapter {
    TextView memo_text, owner_text, Id_text;
    Button memo_button;
    private LayoutInflater mInflater;

    public MemoAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(final Context context, Cursor cursor, ViewGroup parent) {

        //type이 0일때, 메모면 이 레이아웃대로
        if (cursor.getInt(cursor.getColumnIndex(MemoItem.KEY_type)) == 0) {
            View view = mInflater.inflate(R.layout.memoitem_1, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.Id_text = (TextView) view.findViewById(R.id.txtId);
            holder.memo_text = (TextView) view.findViewById(R.id.txtMemo);
            holder.owner_text = (TextView) view.findViewById(R.id.txtOwner);
            view.setTag(holder);
            return view;
        }

        //type이 1일때, 파일이면 이 레이아웃대로
        else if (cursor.getInt(cursor.getColumnIndex(MemoItem.KEY_type)) == 1){
            View view = mInflater.inflate(R.layout.memoitem_2, parent, false);
            ViewHolder holder = new ViewHolder();
            holder.Id_text = (TextView) view.findViewById(R.id.txtId2);
            holder.memo_text = (TextView) view.findViewById(R.id.txtMemo2);
            holder.owner_text = (TextView) view.findViewById(R.id.txtOwner2);
            holder.button = (Button)view.findViewById(R.id.memo_button);

            //파일에 있는 버튼의 리스너, 누르면 파일 다운로드
            //파일의 링크는 데이터베이스 "file"에 저장되어 있고
            //cursor.getString(cursor.getColumnIndex(MemoItem.KEY_file) 로 구할 수 있다..아마도
            holder.button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    Toast.makeText(context.getApplicationContext(), "File type item", Toast.LENGTH_SHORT).show();
                }
            });
            view.setTag(holder);
            return view;
        }

        return null;

    }


    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder  =   (ViewHolder)    view.getTag();
        holder.Id_text.setText(cursor.getString(cursor.getColumnIndex(MemoItem.KEY_ID)));
        holder.owner_text.setText(cursor.getString(cursor.getColumnIndex(MemoItem.KEY_owner)));
        holder.memo_text.setText(cursor.getString(cursor.getColumnIndex(MemoItem.KEY_memo)));

    }

    static class ViewHolder {
        TextView owner_text;
        TextView memo_text;
        TextView Id_text;
        Button button;
    }
}
