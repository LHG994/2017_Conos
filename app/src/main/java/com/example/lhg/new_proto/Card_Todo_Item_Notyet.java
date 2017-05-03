package com.example.lhg.new_proto;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LHG on 2017-04-30.
 */

public class Card_Todo_Item_Notyet {
    private String todo_name;
    private Button botton;

    public void setTodo_name(String todo_name) {
        this.todo_name = todo_name;
    }

    public void setBotton(Button botton) {
        this.botton = botton;
    }

    public String getTodo_name() {
        return todo_name;
    }

    public Button getBotton() {
        return botton;
    }

    public class Card_Todo_Item_Notyet_adapter extends RecyclerView.Adapter<Card_Todo_Item_Notyet_adapter.ViewHolder> {

        private ArrayList<Card_Todo_Item_Notyet> temp_Notyet_array;

        public Card_Todo_Item_Notyet_adapter(ArrayList<Card_Todo_Item_Notyet> Notyet_ItemList) {
            temp_Notyet_array = Notyet_ItemList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate();
            view.setOnClickListener();
            return new ViewHolder();
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.Todo_name.setText(temp_Notyet_array.get(position).getTodo_name());
            holder.Pickup_button.setOnClickListener(new);
        }

        public int getItemCount() {
            return temp_Notyet_array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView Todo_name;
            public Button Pickup_button;

            public ViewHolder(View itemView) {
                super(itemView);
            }
        }

        class Notyet_onclick_listener implements View.OnClickListener {
            @Override
            public void onClick(final View view) {
                //아이템을 삭제하는 부분
            }
        }

        class Notyet_button_onclidk_listener implements View.OnClickListener {
            @Override
            public void onClick(final View view){
                //버튼을 누르면 어떻게 되더라?
                //누른 사람의 아이디를 체크해서
                //그걸 참고로 투두_두잉을 생성
                //그리고 자신은 리스트에서 삭제
            }
        }
    }
}
