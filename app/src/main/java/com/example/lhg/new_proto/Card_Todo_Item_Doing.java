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

public class Card_Todo_Item_Doing {
    private String todo_name;
    private String person_name;
    private Button doing_button;
    private Button undo_button;
    private int personID;

    public void setTodo_name(String todo_name) {
        this.todo_name = todo_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public void setDoing_button(Button doing_button) {
        this.doing_button = doing_button;
    }

    public void setUndo_button(Button undo_button) {
        this.undo_button = undo_button;
    }

    public void setPersonID(int personID) {
        this.personID = personID;
    }

    public String getTodo_name() {
        return todo_name;
    }

    public String getPerson_name() {
        return person_name;
    }

    public Button getDoing_button() {
        return doing_button;
    }

    public Button getUndo_button() {
        return undo_button;
    }

    public int getPersonID() {
        return personID;
    }

    public class Card_Todo_Item_Doing_adapter extends RecyclerView.Adapter<Card_Todo_Item_Doing_adapter.ViewHolder> {

        private ArrayList<Card_Todo_Item_Doing> temp_Doing_array;

        public Card_Todo_Item_Doing_adapter(ArrayList<Card_Todo_Item_Doing> Doing_ItemList) {
            temp_Doing_array = Doing_ItemList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate();
            view.setOnClickListener();
            return new ViewHolder();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.Todo_name.setText(temp_Doing_array.get(position).getTodo_name());
            holder.Person_name.setText(temp_Doing_array.get(position).getPerson_name());
            holder.Doing_button.setOnClickListener(new doing_button_onclidk_listener());
            holder.Undo_button.setOnClickListener(new);
        }

        public int getItemCount() {
            return temp_Doing_array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView Todo_name;
            public TextView Person_name;
            public Button Doing_button;
            public Button Undo_button;

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        class Notyet_onclick_listener implements View.OnClickListener {
            @Override
            public void onClick(final View view) {
                //본체를 누르면 어떻게 되더라?
                //인비지블이었던 부분이 비지블로 바뀌게 됨
            }
        }

        class doing_button_onclidk_listener implements View.OnClickListener {
            @Override
            public void onClick(final View view){
                //버튼을 누르면 어떻게 되더라?
                //이걸 두잉으로 바꾼 장본인이 눌렀으면
                //누른 사람의 아이디를 참고해서
                //투두_던에 아이템을 추가하고
                //자신은 삭제
                //장본인이 아니라면
                //재촉하기 카운트를 하나 올리고
                //수행중인 사람에게 알림을 보내기
            }
        }
    }
}
