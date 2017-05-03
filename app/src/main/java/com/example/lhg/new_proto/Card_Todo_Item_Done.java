package com.example.lhg.new_proto;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LHG on 2017-04-30.
 */

public class Card_Todo_Item_Done {
    private String todo_name;
    private String person_name;
    private int number_of_like = 0;
    private boolean liked = false;
    private Button like_button;
    private Button Undo_button;
    private int personID;

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public void setUndo_button(Button undo_button) {
        Undo_button = undo_button;
    }

    public void setLike_button(Button like_button) {
        this.like_button = like_button;
    }

    public void setNumber_of_like(int number_of_like) {
        this.number_of_like = number_of_like;
    }

    public void setTodo_name(String todo_name) {
        this.todo_name = todo_name;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getPerson_name() {
        return person_name;
    }

    public int getPersonID() {
        return personID;
    }

    public Button getUndo_button() {
        return Undo_button;
    }

    public Button getLike_button() {
        return like_button;
    }

    public int getNumber_of_like() {
        return number_of_like;
    }

    public boolean getLiked() {
        return liked;
    }

    public String getTodo_name() {
        return todo_name;
    }



    public class Card_Todo_Item_Done_adapter extends RecyclerView.Adapter<Card_Todo_Item_Done_adapter.ViewHolder> {

        private ArrayList<Card_Todo_Item_Done> temp_Done_array;

        public Card_Todo_Item_Done_adapter(ArrayList<Card_Todo_Item_Done> Done_ItemList) {
            temp_Done_array = Done_ItemList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate();
            view.setOnClickListener();
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.Todo_name.setText(temp_Done_array.get(position).getTodo_name());
            holder.Person_name.setText(temp_Done_array.get(position).getPerson_name());
            holder.Like_button.setOnClickListener(new like_button_onclidk_listener());
            holder.Undo_button.setOnClickListener(new);
        }

        public int getItemCount() {
            return temp_Done_array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView Todo_name;
            public TextView Person_name;
            public Button Like_button;
            public Button Undo_button;

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        class Notyet_onclick_listener implements View.OnClickListener {
            @Override
            public void onClick(final View view) {
                //본체를 누르면 어떻게 되더라?
                //인비지블이었던 부분이 보이게 됨
            }
        }

        class like_button_onclidk_listener implements View.OnClickListener {
            @Override
            public void onClick(final View view){
                //버튼을 누르면 어떻게 되더라?
                //투두_던으로 바꾼 장본인에겐 버튼 비활성
                //그 밖의 사람들은
                //하트를 눌러 라이크 카운트를 증가
                //카운트가 1 늘 때마다 개인정보란의 카운트도 같이 증가
                //하트를 다시 누르면 라이크 취소

            }
        }
    }

}
