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

import static com.example.lhg.new_proto.BoardFragment.board_itemList;
import static com.example.lhg.new_proto.BoardFragment.recyclerView_board;
import static com.example.lhg.new_proto.R.layout.fragment_board;

/**
 * Created by LHG on 2017-04-11.
 */

public class BoardFragment extends Fragment {

    //recycler view ingredient
    static public RecyclerView recyclerView_board;
    //static public RecyclerView recyclerView_board_fav;
    static public board_adapter mboard_adapter;
    //static public board_fav_adapter mboard_fav_adapter;
    static public ArrayList<board_item> board_itemList;
    //static public ArrayList<board_item> board_fav_itemList;
    private RecyclerView.LayoutManager board_LayoutManager;
    //private RecyclerView.LayoutManager board_fav_LayoutManager;
    //recycler view ingredient end

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(fragment_board, container, false);


/*
        recyclerView_board_fav = (RecyclerView) v.findViewById(R.id.board_fav_recycler);

        board_fav_itemList = new ArrayList<board_item>();

        board_fav_LayoutManager = new LinearLayoutManager(getActivity());

        mboard_fav_adapter = new board_fav_adapter(board_fav_itemList);
        recyclerView_board_fav.setAdapter(mboard_fav_adapter);
        recyclerView_board_fav.setLayoutManager(board_fav_LayoutManager);
        recyclerView_board_fav.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration fav_dividerItemDecoration = new DividerItemDecoration(recyclerView_board_fav.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView_board.addItemDecoration(fav_dividerItemDecoration);
        */


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

}


class board_item {
    private String name;
    private CheckBox button;
    private int state = 0;

    public String getName() {
        return this.name;
    }

    public CheckBox getButton() { return this.button; }

    public int getState() { return this.state; }

    public void setName(String name) {
        this.name = name;
    }

    public void setButton(CheckBox button) { this.button = button; }

    public void setState(int state) { this.state = state; }
}

class board_adapter extends RecyclerView.Adapter<board_adapter.ViewHolder> {
    private ArrayList<board_item> temp_board_array;
    private final board_onclick_listener board_onclick = new board_onclick_listener();

    public board_adapter(ArrayList<board_item> board_itemList) {
        temp_board_array = board_itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_board, parent, false);
        view.setOnClickListener(board_onclick);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.board_name_text.setText(temp_board_array.get(position).getName());
        holder.board_favorite_button.setOnClickListener(new board_button_onclick_listener());
    }

    @Override
    public int getItemCount() {
        return temp_board_array.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView board_name_text;
        public CheckBox board_favorite_button;

        public ViewHolder(View itemView) {
            super(itemView);

            board_name_text = (TextView) itemView.findViewById(R.id.board_name);
            board_favorite_button = (CheckBox)itemView.findViewById(R.id.board_favorite_button);

        }



    }
    //각 아이템에 적용될 리스너
    static class board_onclick_listener implements View.OnClickListener {
        @Override
        public void onClick (final View view) {
            int itemposition  = recyclerView_board.getChildLayoutPosition(view);
            board_item item = board_itemList.get(itemposition);
            Toast.makeText(view.getContext(), item.getName() + " selected" , Toast.LENGTH_LONG).show();
            Intent intent = new Intent(view.getContext(), CardActivity.class);
            view.getContext().startActivity(intent);

        }

    }

    //각 버튼에 적용될 리스너
    class board_button_onclick_listener implements View.OnClickListener {
        @Override
        public void onClick (final View view) {

            Toast.makeText(view.getContext(), "button clicked" , Toast.LENGTH_LONG).show();

        }

    }

}

/*
class board_fav_adapter extends RecyclerView.Adapter<board_fav_adapter.ViewHolder> {
    private ArrayList<board_item> temp_board_array;
    private final board_onclick_listener board_onclick = new board_onclick_listener();

    public board_fav_adapter(ArrayList<board_item> board_itemList) {
        temp_board_array = board_itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_board, parent, false);
        view.setOnClickListener(board_onclick);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.board_name_text.setText(temp_board_array.get(position).getName());
        holder.board_favorite_button.setOnClickListener(new board_button_onclick_listener());
    }

    @Override
    public int getItemCount() {
        return temp_board_array.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView board_name_text;
        public CheckBox board_favorite_button;

        public ViewHolder(View itemView) {
            super(itemView);

            board_name_text = (TextView) itemView.findViewById(R.id.board_name);
            board_favorite_button = (CheckBox)itemView.findViewById(R.id.board_favorite_button);

        }

    }
    //각 아이템에 적용될 리스너
    static class board_onclick_listener implements View.OnClickListener {
        @Override
        public void onClick (final View view) {
            int itemposition  = recyclerView_board.getChildLayoutPosition(view);
            board_item item = board_itemList.get(itemposition);
            Toast.makeText(view.getContext(), item.getName() + " selected" , Toast.LENGTH_LONG).show();
            Intent intent = new Intent(view.getContext(), CardActivity.class);
            view.getContext().startActivity(intent);

        }

    }

    //각 버튼에 적용될 리스너
    class board_button_onclick_listener implements View.OnClickListener {
        @Override
        public void onClick (final View view) {


            Toast.makeText(view.getContext(), "button clicked" , Toast.LENGTH_LONG).show();
        }

    }

}
*/