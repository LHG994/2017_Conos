package com.example.lhg.new_proto;

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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.lhg.new_proto.R.layout.fragment_card;

/**
 * Created by LHG on 2017-04-17.
 */

//이 위에 투두, 메모, 미트업, 어태치를 올려야 됨

public class CardFragment extends Fragment {


    private RecyclerView recyclerView_todo_notyet;
    public static Card_Todo_Item_Notyet_adapter notyet_adapter;
    public static ArrayList<Card_Todo_Item_Notyet> notyet_itemList;
    private RecyclerView.LayoutManager notyet_LayoutManager;

    private RecyclerView recyclerView_todo_doing;
    public static Card_Todo_Item_Doing_adapter doing_adapter;
    public static ArrayList<Card_Todo_Item_Doing> doing_itemList;
    private RecyclerView.LayoutManager doing_LayoutManager;

    private RecyclerView recyclerView_todo_done;
    public static Card_Todo_Item_Done_adapter done_adapter;
    public static ArrayList<Card_Todo_Item_Done> done_itemList;
    private RecyclerView.LayoutManager done_LayoutManager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(fragment_card, container, false);


        //recycler view setting
        recyclerView_todo_notyet = (RecyclerView) v.findViewById(R.id.todo_notyet_recycler);

        notyet_itemList = new ArrayList<Card_Todo_Item_Notyet>();

        notyet_LayoutManager = new LinearLayoutManager(getActivity());

        notyet_adapter = new Card_Todo_Item_Notyet_adapter(notyet_itemList);
        recyclerView_todo_notyet.setAdapter(notyet_adapter);
        recyclerView_todo_notyet.setLayoutManager(notyet_LayoutManager);
        recyclerView_todo_notyet.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration_notyet = new DividerItemDecoration(recyclerView_todo_notyet.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView_todo_notyet.addItemDecoration(dividerItemDecoration_notyet);
        //recycler view setting end

        //recycler view setting
        recyclerView_todo_doing = (RecyclerView) v.findViewById(R.id.todo_doing_recycler);

        doing_itemList = new ArrayList<Card_Todo_Item_Doing>();

        doing_LayoutManager = new LinearLayoutManager(getActivity());

        doing_adapter = new Card_Todo_Item_Doing_adapter(doing_itemList);
        recyclerView_todo_doing.setAdapter(doing_adapter);
        recyclerView_todo_doing.setLayoutManager(doing_LayoutManager);
        recyclerView_todo_doing.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration_doing = new DividerItemDecoration(recyclerView_todo_notyet.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView_todo_notyet.addItemDecoration(dividerItemDecoration_doing);
        //recycler view setting end

        //recycler view setting
        recyclerView_todo_done = (RecyclerView) v.findViewById(R.id.todo_done_recycler);

        done_itemList = new ArrayList<Card_Todo_Item_Done>();

        done_LayoutManager = new LinearLayoutManager(getActivity());

        done_adapter = new Card_Todo_Item_Done_adapter(done_itemList);
        recyclerView_todo_done.setAdapter(done_adapter);
        recyclerView_todo_done.setLayoutManager(done_LayoutManager);
        recyclerView_todo_done.setItemAnimator(new DefaultItemAnimator());

        DividerItemDecoration dividerItemDecoration_done = new DividerItemDecoration(recyclerView_todo_notyet.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView_todo_notyet.addItemDecoration(dividerItemDecoration_done);
        //recycler view setting end

        Card_Todo_Item_Notyet item1 = new Card_Todo_Item_Notyet();
        item1.setTodo_name("Hellooooo1");
        notyet_itemList.add(item1);
        Card_Todo_Item_Notyet item2 = new Card_Todo_Item_Notyet();
        item2.setTodo_name("Hellooooo2");
        notyet_itemList.add(item2);
        Card_Todo_Item_Notyet item3 = new Card_Todo_Item_Notyet();
        item3.setTodo_name("Hellooooo3");
        notyet_itemList.add(item3);
        notyet_adapter.notifyDataSetChanged();

        return v;
    }


    //notyet item
    public class Card_Todo_Item_Notyet {
        private String todo_name;
        private Button pickup_button;
        private Button delete_button;

        public void setTodo_name(String todo_name) {
            this.todo_name = todo_name;
        }

        public void setPickup_button(Button pickup_button) {
            this.pickup_button = pickup_button;
        }

        public void setDelete_button(Button delete_button) {
            this.delete_button = delete_button;
        }

        public String getTodo_name() {
            return todo_name;
        }

        public Button getPickup_button() {
            return pickup_button;
        }

        public Button getDelete_button() {
            return delete_button;
        }
    }

    public class Card_Todo_Item_Notyet_adapter extends RecyclerView.Adapter<Card_Todo_Item_Notyet_adapter.ViewHolder> {

        private ArrayList<Card_Todo_Item_Notyet> temp_Notyet_array;

        public Card_Todo_Item_Notyet_adapter(ArrayList<Card_Todo_Item_Notyet> Notyet_ItemList) {
            temp_Notyet_array = Notyet_ItemList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_todo, parent, false);
            view.setOnLongClickListener(new Notyet_item_onclick_listener());
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.Todo_name.setText(temp_Notyet_array.get(position).getTodo_name());

            final int itemposition = position;

            class notyet_button_onclick_listener implements View.OnClickListener {
                @Override
                public void onClick(final View view) {
                    Card_Todo_Item_Notyet item = notyet_itemList.get(itemposition);
                    //여기서 아이템을 가리키게 되었으니까..

                    if(view.getId() == R.id.todo_button) {
                        Toast.makeText(view.getContext(), item.getTodo_name() + ", normal button selected", Toast.LENGTH_LONG).show();
                    }

                    else{
                        Toast.makeText(view.getContext(), item.getTodo_name() + ", undo button selected", Toast.LENGTH_LONG).show();
                        notyet_itemList.remove(itemposition);
                        notyet_adapter.notifyDataSetChanged();
                    }

                }
            }

            holder.Pickup_button.setOnClickListener(new notyet_button_onclick_listener());
            holder.Delete_button.setOnClickListener(new notyet_button_onclick_listener());

        }

        public int getItemCount() {
            return temp_Notyet_array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView Todo_name;
            public Button Pickup_button;
            public Button Delete_button;

            public ViewHolder(View itemView) {
                super(itemView);
                Todo_name = (TextView) itemView.findViewById(R.id.todo_name);
                Pickup_button = (Button) itemView.findViewById(R.id.todo_button);
                Delete_button = (Button) itemView.findViewById(R.id.todo_undo_button);
            }
        }

        class Notyet_item_onclick_listener implements View.OnLongClickListener {
            @Override
            public boolean onLongClick(View view) {
                int itemposition = recyclerView_todo_notyet.getChildLayoutPosition(view);
                Toast.makeText(view.getContext(), "Long clicked", Toast.LENGTH_LONG).show();

                Card_Todo_Item_Notyet item = notyet_itemList.get(itemposition);

                Button d_button = item.getDelete_button();

                return false;
            }
        }



        class Notyet_undo_button_onclick_listener implements View.OnClickListener {
            @Override
            public void onClick(final View view) {
                View parent_view = (View) view.getParent();
                RecyclerView recyclerView = (RecyclerView) parent_view.getParent();
                final int position = recyclerView.getChildLayoutPosition(parent_view);
                Card_Todo_Item_Notyet item = notyet_itemList.get(position);
                //지우는 버튼
            }
        }
    }
    //notyet item end

    //doing item
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
    }

    public class Card_Todo_Item_Doing_adapter extends RecyclerView.Adapter<Card_Todo_Item_Doing_adapter.ViewHolder> {

        private ArrayList<Card_Todo_Item_Doing> temp_Doing_array;

        public Card_Todo_Item_Doing_adapter(ArrayList<Card_Todo_Item_Doing> Doing_ItemList) {
            temp_Doing_array = Doing_ItemList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_todo, parent, false);
            view.setOnLongClickListener(new doing_item_onclick_listener());
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.Todo_name.setText(temp_Doing_array.get(position).getTodo_name());
            holder.Person_name.setText(temp_Doing_array.get(position).getPerson_name());
            holder.Doing_button.setOnClickListener(new doing_button_onclick_listener());
            holder.Undo_button.setOnClickListener(new doing_undo_button_onclick_listener());
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

        class doing_item_onclick_listener implements View.OnLongClickListener {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        }

        class doing_button_onclick_listener implements View.OnClickListener {
            @Override
            public void onClick(final View view) {
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

        class doing_undo_button_onclick_listener implements View.OnClickListener {
            @Override
            public void onClick(final View view) {
                //지우는 버튼
            }
        }
    }
    //doing item end

    //done item
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
    }

    public class Card_Todo_Item_Done_adapter extends RecyclerView.Adapter<Card_Todo_Item_Done_adapter.ViewHolder> {

        private ArrayList<Card_Todo_Item_Done> temp_Done_array;

        public Card_Todo_Item_Done_adapter(ArrayList<Card_Todo_Item_Done> Done_ItemList) {
            temp_Done_array = Done_ItemList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_todo, parent, false);
            view.setOnLongClickListener(new done_item_onclick_listener());
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.Todo_name.setText(temp_Done_array.get(position).getTodo_name());
            holder.Person_name.setText(temp_Done_array.get(position).getPerson_name());
            holder.Like_button.setOnClickListener(new like_button_onclick_listener());
            holder.Undo_button.setOnClickListener(new done_undo_button_onclick_listener());
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

        class done_item_onclick_listener implements View.OnLongClickListener {
            @Override
            public boolean onLongClick(View view) {
                return false;
            }
        }

        class like_button_onclick_listener implements View.OnClickListener {
            @Override
            public void onClick(final View view) {
                //버튼을 누르면 어떻게 되더라?
                //투두_던으로 바꾼 장본인에겐 버튼 비활성
                //그 밖의 사람들은
                //하트를 눌러 라이크 카운트를 증가
                //카운트가 1 늘 때마다 개인정보란의 카운트도 같이 증가
                //하트를 다시 누르면 라이크 취소

            }
        }

        class done_undo_button_onclick_listener implements View.OnClickListener {
            @Override
            public void onClick(final View view) {
                //지우는 버튼
            }
        }
    }
    //done item end


}


