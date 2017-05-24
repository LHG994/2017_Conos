package com.example.lhg.new_proto;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.mrapp.android.dialog.MaterialDialog;

import static com.example.lhg.new_proto.R.layout.fragment_card;

/**
 * Created by LHG on 2017-04-17.
 */

//이 위에 투두, 메모, 미트업, 어태치를 올려야 됨

public class CardFragment extends Fragment {
    private String url = "http://166.62.32.120:5000/";
    private String b_id;
    private String token;
    public String [] todoId = new String[10];
    public String [] doingName = new String[10];
    public String [] doneName = new String[10];
    public String [] doingId = new String[10];
    public String [] doneId = new String[10];

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

    Handler handler = null;

    public void setToken(String token) {
        this.token= token;
    }
    public String getToken() {
        return token;
    }
    public void setB_id(String b_id) {
        this.b_id= b_id;
    }
    public String getB_id() {
        return b_id;
    }
    public void setUrl(String url) {
        this.url= url;
    }
    public String getUrl() {
        return url;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(fragment_card, container, false);

        handler = new Handler();


        new Thread(new Runnable() {
            @Override
            public void run() {

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

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //recycler view setting
                recyclerView_todo_doing = (RecyclerView) v.findViewById(R.id.todo_doing_recycler);

                doing_itemList = new ArrayList<Card_Todo_Item_Doing>();

                doing_LayoutManager = new LinearLayoutManager(getActivity());

                doing_adapter = new Card_Todo_Item_Doing_adapter(doing_itemList);
                recyclerView_todo_doing.setAdapter(doing_adapter);
                recyclerView_todo_doing.setLayoutManager(doing_LayoutManager);
                recyclerView_todo_doing.setItemAnimator(new DefaultItemAnimator());

                DividerItemDecoration dividerItemDecoration_doing = new DividerItemDecoration(recyclerView_todo_doing.getContext(), DividerItemDecoration.VERTICAL);
                recyclerView_todo_doing.addItemDecoration(dividerItemDecoration_doing);
                //recycler view setting end
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //recycler view setting
                recyclerView_todo_done = (RecyclerView) v.findViewById(R.id.todo_done_recycler);

                done_itemList = new ArrayList<Card_Todo_Item_Done>();

                done_LayoutManager = new LinearLayoutManager(getActivity());

                done_adapter = new Card_Todo_Item_Done_adapter(done_itemList);
                recyclerView_todo_done.setAdapter(done_adapter);
                recyclerView_todo_done.setLayoutManager(done_LayoutManager);
                recyclerView_todo_done.setItemAnimator(new DefaultItemAnimator());

                DividerItemDecoration dividerItemDecoration_done = new DividerItemDecoration(recyclerView_todo_done.getContext(), DividerItemDecoration.VERTICAL);
                recyclerView_todo_doing.addItemDecoration(dividerItemDecoration_done);
                //recycler view setting end
            }
        }).start();

        ////////////////////////////////////////////////////////////////
        ContentValues values = new ContentValues();

        setB_id(getArguments().getString("b_id"));
        setToken(getArguments().getString("token"));
        //String token = "eyJleHAiOjE0OTUwNDUxNzEsImFsZyI6IkhTMjU2IiwiaWF0IjoxNDk0NDQwMzcxfQ.eyJ1c2VyX25hbWUiOiJHb29kIE5hbWUiLCJ1c2VyX2lkIjoxLCJ1c2VyX2VtYWlsIjoic29tZTFAZ29vZCJ9.1kTLbqMBSsp1EtV0IK6WW00nEQ5GuzYO-GMIGgktIUQ";


        // AsyncTask를 통해 HttpURLConnection 수행.
        NetworkTask networkTask = new NetworkTask(null,null);
        networkTask.execute();




        return v;
    }


    //notyet item
    public class Card_Todo_Item_Notyet {
        private String todo_name;
        private String todo_id;
        private Button pickup_button;
        private Button delete_button;

        public void setTodo_name(String todo_name) {
            this.todo_name = todo_name;
        }

        public void setTodo_id(String todo_id) {
            this.todo_id = todo_id;
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

        public String getTodo_id() { return todo_id; }

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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_todo_notyet, parent, false);
            view.setOnLongClickListener(new Notyet_item_onclick_listener());
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.Todo_name.setText(temp_Notyet_array.get(position).getTodo_name());

            //리스너에서 아이템의 변수들을 사용 가능하도록(get으로 버튼 등을 가져올 수 있도록) 세팅해준다.
            final int itemposition = position;
            Card_Todo_Item_Notyet item = notyet_itemList.get(itemposition);

            class notyet_button_onclick_listener implements View.OnClickListener {
                @Override
                public void onClick(final View view) {
                    Card_Todo_Item_Notyet item = notyet_itemList.get(itemposition);
                    //여기서 아이템을 가리키게 되었으니까..

                    Toast.makeText(view.getContext(), item.getTodo_name() + ", normal button selected", Toast.LENGTH_LONG).show();

                    Card_Todo_Item_Doing doing_item = new Card_Todo_Item_Doing();


                    doing_item.setTodo_name(item.getTodo_name());
                    doing_itemList.add(doing_item);




                    doing_adapter.notifyItemInserted(doing_itemList.size());
                    notyet_adapter.notifyItemRemoved(itemposition);

                    notyet_itemList.remove(itemposition);

                    /*
                    NetworkTask networkTask = new NetworkTask("mvGoing",item.getTodo_id());
                    networkTask.execute();
                    */


                }
            }

            holder.Pickup_button.setOnClickListener(new notyet_button_onclick_listener());

        }

        public int getItemCount() {
            return temp_Notyet_array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView Todo_name;
            public Button Pickup_button;

            public ViewHolder(View itemView) {
                super(itemView);
                Todo_name = (TextView) itemView.findViewById(R.id.todo_name);
                Pickup_button = (Button) itemView.findViewById(R.id.todo_button);
            }
        }

        class Notyet_item_onclick_listener implements View.OnLongClickListener {
            @Override
            public boolean onLongClick(View view) {
                final int position = recyclerView_todo_notyet.getChildLayoutPosition(view);
                final View innerV = view;


                Card_Todo_Item_Notyet item = notyet_itemList.get(position);
                class ok_button_listener implements DialogInterface.OnClickListener {
                    @Override
                    public void onClick(DialogInterface di, int i) {
                        Card_Todo_Item_Notyet item = notyet_itemList.get(position);
                        Toast.makeText(innerV.getContext(), "ok button", Toast.LENGTH_LONG).show();
                        /*notyet_itemList.remove(position);
                        notyet_adapter.notifyDataSetChanged();*/
                        NetworkTask networkTask = new NetworkTask("del",item.getTodo_id());
                        networkTask.execute();
                        //그리고, 서버에 알려야지

                    }
                }

                MaterialDialog.Builder dialogBuilder = new MaterialDialog.Builder(getActivity());
                dialogBuilder.setTitle("Delete Item");
                dialogBuilder.setMessage("삭제된 항목은 복구할 수 없습니다.");
                dialogBuilder.setPositiveButton(android.R.string.ok, new ok_button_listener());
                dialogBuilder.setNegativeButton(android.R.string.cancel, null);
                MaterialDialog dialog = dialogBuilder.create();
                dialog.show();

                return false;
            }
        }

    }
    //notyet item end

    //doing item
    public class Card_Todo_Item_Doing {
        private String todo_name;
        private String todo_id;
        private String person_name;
        private Button doing_button;
        private int personID = 0;

        public void setTodo_name(String todo_name) {
            this.todo_name = todo_name;
        }

        public void setTodo_id(String todo_id) {
            this.todo_id = todo_id;
        }

        public void setPerson_name(String person_name) {
            this.person_name = person_name;
        }

        public void setDoing_button(Button doing_button) {
            this.doing_button = doing_button;
        }

        public void setPersonID(int personID) {
            this.personID = personID;
        }

        public String getTodo_name() {
            return todo_name;
        }

        public String getTodo_id() {
            return todo_id;
        }

        public String getPerson_name() {
            return person_name;
        }

        public Button getDoing_button() {
            return doing_button;
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_todo_doing, parent, false);
            view.setOnLongClickListener(new Doing_item_onclick_listener());
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.Todo_name.setText(temp_Doing_array.get(position).getTodo_name());
            //holder.Person_name.setText("이현규가 수행중!");
            holder.Doing_button.setText("Complete!");

            //이걸 하기로 한 사람이 나라면
            //완료 버튼으로 바뀌고
            //내가 아니라면
            //재촉하기 버튼으로 바뀌어야겠지
            //텍스트는 위처럼 holder로 설정해주고
            //리스너는 아래에서 if문으로 배정해주자

            final int itemposition = position;

            class doing_button_onclick_listener implements View.OnClickListener {
                @Override
                public void onClick(final View view) {
                    Card_Todo_Item_Doing item = doing_itemList.get(itemposition);
                    //여기서 아이템을 가리키게 되었으니까..

                    Toast.makeText(view.getContext(), item.getTodo_name() + ", normal button selected", Toast.LENGTH_LONG).show();

                    NetworkTask networkTask = new NetworkTask("mvDone",item.getTodo_id());
                    networkTask.execute();
                }
            }

            holder.Doing_button.setOnClickListener(new doing_button_onclick_listener());
        }

        public int getItemCount() {
            return temp_Doing_array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView Todo_name;
            public TextView Person_name;
            public Button Doing_button;

            public ViewHolder(View itemView) {
                super(itemView);

                Todo_name = (TextView) itemView.findViewById(R.id.todo_name);
                Doing_button = (Button) itemView.findViewById(R.id.todo_button);
                Person_name = (TextView)itemView.findViewById(R.id.todo_person_name);

            }
        }

        class Doing_item_onclick_listener implements View.OnLongClickListener {
            @Override
            public boolean onLongClick(View view) {
                final int position = recyclerView_todo_doing.getChildLayoutPosition(view);
                final View innerV = view;

                Card_Todo_Item_Doing item = doing_itemList.get(position);

                class ok_button_listener implements DialogInterface.OnClickListener {
                    @Override
                    public void onClick(DialogInterface di, int i) {
                        Toast.makeText(innerV.getContext(), "ok button", Toast.LENGTH_LONG).show();
                        //서버에 알리고 리프레쉬
                    }
                }

                MaterialDialog.Builder dialogBuilder = new MaterialDialog.Builder(getActivity());
                dialogBuilder.setTitle("Undo Item");
                dialogBuilder.setMessage("항목이 todo로 돌아갑니다.");
                dialogBuilder.setPositiveButton(android.R.string.ok, new ok_button_listener());
                dialogBuilder.setNegativeButton(android.R.string.cancel, null);
                MaterialDialog dialog = dialogBuilder.create();
                dialog.show();

                return false;
            }
        }


    }
    //doing item end

    //done item
    public class Card_Todo_Item_Done {
        private String todo_name;
        private String todo_id;
        private String person_name;
        private int number_of_like = 0;
        private boolean liked = false;
        private Button like_button;
        private int personID;

        public void setPerson_name(String person_name) {
            this.person_name = person_name;
        }

        public void setPersonID(int personID) {
            this.personID = personID;
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

        public void setTodo_id(String todo_id) {
            this.todo_id = todo_id;
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

        public String getTodo_id() {
            return todo_id;
        }
    }

    public class Card_Todo_Item_Done_adapter extends RecyclerView.Adapter<Card_Todo_Item_Done_adapter.ViewHolder> {

        private ArrayList<Card_Todo_Item_Done> temp_Done_array;

        public Card_Todo_Item_Done_adapter(ArrayList<Card_Todo_Item_Done> Done_ItemList) {
            temp_Done_array = Done_ItemList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_todo_done, parent, false);
            view.setOnLongClickListener(new Done_item_onclick_listener());
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.Todo_name.setText(temp_Done_array.get(position).getTodo_name());
            holder.Person_name.setText(temp_Done_array.get(position).getPerson_name());

            //완료된 항목엔 취소선을 그어놓자
            //그리고 아래에는 누가 완료했는지 적어놓고
            //내가 한 게 아니라면
            //좋아요 버튼이 되겠고
            //liked변수를 써서 내가 좋아요를 눌렀는지 판단하는거랑
            //numberoflike를 써서 좋아요의 수를 판단?
            //장형이 만든거랑은 방식이 좀 다른 것 같으니, 읽어보고 다시 생각해야
            //내가 한 거라면
            //버튼은 누를 수 없지만, 좋아요의 수가 보인다.

            holder.Like_button.setBackground(getResources().getDrawable(R.drawable.heart1));



            final int itemposition = position;

            class done_button_onclick_listener implements View.OnClickListener {
                @Override
                public void onClick(final View view) {
                    if(holder.statement == 0){
                        holder.Like_button.setBackground(getResources().getDrawable(R.drawable.heart2));
                        holder.statement = 1;
                    }

                    else{
                        holder.Like_button.setBackground(getResources().getDrawable(R.drawable.heart1));
                        holder.statement = 0;
                    }

                }

            }

            holder.Like_button.setOnClickListener(new done_button_onclick_listener());
        }

        public int getItemCount() {
            return temp_Done_array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public TextView Todo_name;
            public TextView Person_name;
            public ImageButton Like_button;
            public int statement = 0;

            public ViewHolder(View itemView) {
                super(itemView);

                Todo_name = (TextView) itemView.findViewById(R.id.todo_name);
                Like_button = (ImageButton) itemView.findViewById(R.id.todo_button);
                Person_name = (TextView)itemView.findViewById(R.id.todo_person_name);

            }
        }

        class Done_item_onclick_listener implements View.OnLongClickListener {
            @Override
            public boolean onLongClick(View view) {
                final int position = recyclerView_todo_done.getChildLayoutPosition(view);
                final View innerV = view;

                Card_Todo_Item_Done item = done_itemList.get(position);

                class ok_button_listener implements DialogInterface.OnClickListener {
                    @Override
                    public void onClick(DialogInterface di, int i) {
                        Toast.makeText(innerV.getContext(), "ok button", Toast.LENGTH_LONG).show();
                        //서버에 알리고 리프레쉬
                    }
                }

                MaterialDialog.Builder dialogBuilder = new MaterialDialog.Builder(getActivity());
                dialogBuilder.setTitle("Undo Item");
                dialogBuilder.setMessage("항목이 doing으로 돌아갑니다.");
                dialogBuilder.setPositiveButton(android.R.string.ok, new ok_button_listener());
                dialogBuilder.setNegativeButton(android.R.string.cancel, null);
                MaterialDialog dialog = dialogBuilder.create();
                dialog.show();

                return false;
            }
        }

    }
    //done item end

    public class NetworkTask extends AsyncTask<Void, Void, String[]> {

        private String url;
        private String token;
        private String order;
        private String t_id;
        private String b_id;

        public NetworkTask( String order,String t_id) {
            this.url = getUrl();
            this.token = getToken();
            this.order = order;
            this.t_id =t_id;
            this.b_id = getB_id();
        }

        @Override
        protected String[] doInBackground(Void... params) {
            FunctionResult functionResult = new FunctionResult();
            this.b_id = getB_id();
            this.token = getToken();
            this.url = getUrl();

            if(order!=null){
                if(order.equals("del")){
                    ContentValues values = new ContentValues();
                    values.put("todo_id",t_id);
                    String sss = functionResult.stringQuest(url,"delTodo",values,token);//투두 제거
                }
                else if(order.equals("mvGoing")){
                    ContentValues values = new ContentValues();
                    values.put("todo_id",t_id);
                    String sss = functionResult.stringQuest(url,"moveOngoing",values,token);//투두 Ongoing으로
                }
                else if(order.equals("mvDone")){
                    ContentValues values = new ContentValues();
                    values.put("todo_ongoing_id",t_id);
                    String sss = functionResult.stringQuest(url,"moveDone",values,token);//투두 Done 으로
                }
            }
            /*if(order.equals("del")){
                ContentValues values = new ContentValues();
                values.put("todo_id",t_id);
                String sss = functionResult.stringQuest(url,"delTodo",values,token);//투두 제거

            }*/
            //else if(order.equals(null)){}

            String [] res = functionResult.todoQuest(url,"notyet_name",b_id,token);
            doingName = functionResult.todoQuest(url,"doing_name",b_id,token);
            doneName = functionResult.todoQuest(url,"done_name",b_id,token);
            todoId = functionResult.todoQuest(url,"notyet_id",b_id,token);
            doingId = functionResult.todoQuest(url,"doing_id",b_id,token);
            doneId = functionResult.todoQuest(url,"done_id",b_id,token);

            return res;
        }

        @Override
        protected void onPostExecute(String[] s) {
            super.onPostExecute(s);
            notyet_itemList.clear();
            doing_itemList.clear();
            done_itemList.clear();
            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            if (!s[0].equals("")) {
                for (int i = 0; i < s.length; i++) {
                    Card_Todo_Item_Notyet item = new Card_Todo_Item_Notyet();
                    item.setTodo_name(s[i]);
                    item.setTodo_id(todoId[i]);

                    //Log.i("tokenn",b_id);
                    notyet_itemList.add(item);
                }
            } else {
                Card_Todo_Item_Notyet item = new Card_Todo_Item_Notyet();
                item.setTodo_name("EMPTY");
                notyet_itemList.add(item);
            }

            if (!doingId[0].equals("")) {
                for (int i = 0; i < doingId.length; i++) {
                    Card_Todo_Item_Doing item2 = new Card_Todo_Item_Doing();
                    item2.setTodo_name(doingName[i]);
                    item2.setTodo_id(doingId[i]);
                    doing_itemList.add(item2);
                }
            } else {
                Card_Todo_Item_Doing item2 = new Card_Todo_Item_Doing();
                item2.setTodo_name("EMPTY");
                doing_itemList.add(item2);
            }

            if (!doneId[0].equals("")) {
                for (int i = 0; i < doneId.length; i++) {
                    Card_Todo_Item_Done item3 = new Card_Todo_Item_Done();
                    item3.setTodo_name(doneName[i]);
                    item3.setTodo_id(doneId[i]);
                    done_itemList.add(item3);
                }
            } else {
                Card_Todo_Item_Done item3 = new Card_Todo_Item_Done();
                item3.setTodo_name("EMPTY");
                done_itemList.add(item3);
            }

            notyet_adapter.notifyDataSetChanged();
            doing_adapter.notifyDataSetChanged();
            done_adapter.notifyDataSetChanged();
        }
    }
}


