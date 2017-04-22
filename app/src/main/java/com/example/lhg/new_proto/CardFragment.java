package com.example.lhg.new_proto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.lhg.new_proto.BoardFragment.board_itemList;
import static com.example.lhg.new_proto.BoardFragment.recyclerView_board;
import static com.example.lhg.new_proto.CardFragment.card_itemList;
import static com.example.lhg.new_proto.R.layout.fragment_card;

/**
 * Created by LHG on 2017-04-17.
 */

public class CardFragment extends Fragment {

    //recycler view ingredient
    static public RecyclerView recyclerView_card;
    static public card_adapter mcard_adapter;
    static public ArrayList<card_item> card_itemList;
    private RecyclerView.LayoutManager card_LayoutManager;
    //recycler view ingredient end

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_card, container, false);



        return v;
    }
}

class card_item {
    private String Upper_name;
    private String Item_name;
    Fragment fragment;
    private String Under_name;
    private Button button;

    public String getUpper_name() { return Upper_name; }

    public String getUnder_name() { return Under_name; }

    public Fragment get_cardFragment() { return fragment; }

    public String getItem_name() { return Item_name; }

    public Button getButton() { return this.button; }

    public void setUpper_name(String Upper_name) { this.Upper_name = Upper_name; }

    public void setUnder_name(String Under_name) { this.Under_name = Under_name; }

    public void set_cardFragment(Fragment fragment) { this.fragment = fragment; }

    public void setItem_name(String Item_name) { this.Item_name = Item_name; }

    public void setButton(Button button) { this.button = button; }
}

class card_adapter extends RecyclerView.Adapter<card_adapter.ViewHolder> {
    private ArrayList<card_item> temp_card_array;
    private final card_onclick_listener card_onclick = new card_onclick_listener();

    public card_adapter(ArrayList<board_item> board_itemList) {
        temp_card_array = card_itemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.Upper_name_text.setText(temp_card_array.get(position).getUpper_name());
        holder.Under_name_text.setText(temp_card_array.get(position).getUnder_name());
        holder.Item_name_text.setText(temp_card_array.get(position).getItem_name());
    }
    //여기가 그려주는 부분..?

    @Override
    public int getItemCount() {
        return temp_card_array.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView Upper_name_text;
        public TextView Under_name_text;
        public TextView Item_name_text;
        public Fragment fragment;
        public Button card_button;

        public ViewHolder(View itemView) {
            super(itemView);

            Upper_name_text = (TextView) itemView.findViewById(R.id.Upper_name);
            Under_name_text = (TextView) itemView.findViewById(R.id.Under_name);
            Item_name_text = (TextView) itemView.findViewById(R.id.Item_name);
            fragment = new Todo_cardviewFragment();
            this.card_button = (Button)itemView.findViewById(R.id.card_button);

        }
    }
    //각 아이템에 적용될 리스너
    static class card_onclick_listener implements View.OnClickListener {
        @Override
        public void onClick (final View view) {
            int itemposition  = recyclerView_board.getChildLayoutPosition(view);
            board_item item = board_itemList.get(itemposition);
            Toast.makeText(view.getContext(), item.getName() + " selected" , Toast.LENGTH_LONG).show();
            Intent intent = new Intent(view.getContext(), CardActivity.class);
            view.getContext().startActivity(intent);

        }

    }


}