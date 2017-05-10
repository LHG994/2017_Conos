package com.example.lhg.new_proto;

import android.content.Intent;
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

        board_item item = new board_item();
        item.setName("Hello");
        board_itemList.add(item);
        mboard_adapter.notifyDataSetChanged();
        //recycler view setting end

        return v;
    }

    public static class board_item {
        private String name;
        private CheckBox button;
        private int state = 0;

        public String getName() {
            return this.name;
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
                Intent intent = new Intent(view.getContext(), CardActivity.class);
                view.getContext().startActivity(intent);

            }

        }

    }

}