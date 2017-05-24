package com.example.lhg.new_proto;

import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.lhg.new_proto.R.layout.fragment_board;

/**
 * Created by LHG on 2017-04-11.
 */

public class BoardFragment extends Fragment {

    //recycler view ingredient
    private RecyclerView recyclerView_board;
    public static board_adapter mboard_adapter;
    public static ArrayList<board_item> board_itemList;
    private RecyclerView.LayoutManager board_LayoutManager;
    //recycler view ingredient end
    /////// 토큰 ////
    public String token;
    public String b_id;
    ContentValues cValues = new ContentValues();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(fragment_board, container, false);

        //recycler view setting
        recyclerView_board = (RecyclerView) v.findViewById(R.id.board_recycler);

        board_itemList = new ArrayList<board_item>();

        board_LayoutManager = new LinearLayoutManager(getActivity());

        mboard_adapter = new board_adapter(board_itemList);
        recyclerView_board.setAdapter(mboard_adapter);
        recyclerView_board.setLayoutManager(board_LayoutManager);
        recyclerView_board.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView_board.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView_board.addItemDecoration(dividerItemDecoration);

        ////////////////////////////////////////////////////////////////
        String url = "http://166.62.32.120:5000/";

        token = getArguments().getString("token");
        //token = "eyJleHAiOjE0OTUwNTE1NDMsImFsZyI6IkhTMjU2IiwiaWF0IjoxNDk0NDQ2NzQzfQ.eyJ1c2VyX25hbWUiOiJHb29kIE5hbWUiLCJ1c2VyX2lkIjoxLCJ1c2VyX2VtYWlsIjoic29tZTFAZ29vZCJ9.dcJJT-O65wtuY628T9A4QgUfUBML9344VbbFx3ig3ws";

        // AsyncTask를 통해 HttpURLConnection 수행.
        NetworkTask networkTask = new NetworkTask(url, null,token);
        networkTask.execute();

        return v;
    }

    public static class board_item {
        private String name;
        private String id;
        private CheckBox button;
        private int state = 0;

        public String getName() {
            return this.name;
        }

        public String getId() {
            return this.id;
        }

        public CheckBox getButton() {
            return this.button;
        }

        public int getState() {
            return this.state;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setId(String id){this.id = id;}

        public void setButton(CheckBox button) {
            this.button = button;
        }

        public void setState(int state) {
            this.state = state;
        }
    }

    public class board_adapter extends RecyclerView.Adapter<board_adapter.ViewHolder> {
        private ArrayList<board_item> temp_board_array;

        public board_adapter(ArrayList<board_item> board_itemList) {
            temp_board_array = board_itemList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_board, parent, false);
            view.setOnClickListener(new board_onclick_listener());
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final int itemposition = position;

            holder.board_name_text.setText(temp_board_array.get(position).getName());

            class board_button_onclick_listener implements View.OnClickListener {
                @Override
                public void onClick(final View view) {
                    board_item item = board_itemList.get(itemposition);
                    Toast.makeText(view.getContext(), "button clicked", Toast.LENGTH_LONG).show();

                    if(item.getState() == 0){

                    }

                    else {

                    }

                }

            }

            holder.board_favorite_button.setOnClickListener(new board_button_onclick_listener());
        }

        @Override
        public int getItemCount() {
            return temp_board_array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public TextView board_name_text;
            public CheckBox board_favorite_button;

            public ViewHolder(View itemView) {
                super(itemView);

                board_name_text = (TextView) itemView.findViewById(R.id.board_name);
                board_favorite_button = (CheckBox) itemView.findViewById(R.id.board_favorite_button);

            }
        }

        //각 아이템에 적용될 리스너
        class board_onclick_listener implements View.OnClickListener {
            @Override
            public void onClick(final View view) {
                int itemposition = recyclerView_board.getChildLayoutPosition(view);
                board_item item = board_itemList.get(itemposition);
                Toast.makeText(view.getContext(), item.getName() + " selected", Toast.LENGTH_LONG).show();

                b_id = item.getId();

                Intent intent = new Intent(view.getContext(), CardActivity.class);
                intent.putExtra("token",token);
                intent.putExtra("b_id",b_id);
                view.getContext().startActivity(intent);

            }

        }

    }
    public class NetworkTask extends AsyncTask<Void, Void, String[]> {

        private String url;
        private ContentValues values;
        private String token;

        public NetworkTask(String url, ContentValues values, String token) {
            this.url = url;
            this.values = values;
            this.token = token;
        }

        @Override
        protected String[] doInBackground(Void... params) {
            FunctionResult functionResult = new FunctionResult();
            String[] name = functionResult.arrayQuest(url, "user_board_name", null, token);
            String[] id = functionResult.arrayQuest(url, "user_board_id", null, token);

            for(int i=0; i<id.length; i++){
                cValues.put(id[i],name[i]);
            }

            return id;
        }

        @Override
        protected void onPostExecute(String[] s) {
            super.onPostExecute(s);

            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.

            for (int i = 0; i < s.length; i++) {
                board_item item = new board_item();
                item.setId(s[i]);
                item.setName(cValues.getAsString(s[i]));
                board_itemList.add(item);
                mboard_adapter.notifyDataSetChanged();
            }
        }
    }

}