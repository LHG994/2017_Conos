package com.example.lhg.new_proto;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by LHG on 2017-05-24.
 */

    //서버에서 메모/아이템 목록 받아오는 부분을 부탁함
    //시간 순서대로 메모/아이템이 등록되었으면 좋겠음

public class MemoActivity extends AppCompatActivity {

    private MemoAdapter memoAdapter;
    ListView listView;
    Cursor cursor;
    MemoRepo memoRepo;
    private final static String TAG = MemoActivity.class.getName().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        memoRepo = new MemoRepo(this);
        cursor = memoRepo.getMemotList();
        memoAdapter = new MemoAdapter(MemoActivity.this, cursor, 0);
        listView = (ListView)findViewById(R.id.memolist);
        listView.setAdapter(memoAdapter);


        //데이터베이스는 앱을 껐다 킨다고 해서 지워지는게 아니라서
        //테스트를 해보려면 앱을 삭제해야 됨
        //사실 내가 아직 적절한 초기화법을 몰라서.. 알아내면 적용시켜줘
        if(cursor == null) insertDummy();

        Button write_button = (Button)findViewById(R.id.button_write);
        Button upload_button = (Button)findViewById(R.id.button_upload);
        Button gather_button = (Button)findViewById(R.id.button_gatherfile);

        write_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //메모를 등록하는 부분
                //등록할 때 사용할 다이얼로그는 곧 만들겠음
                //서버쪽 코딩 부탁

            }
        });

        upload_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                //파일 선택하는 창 여는 부분
                //받아온 파일을 서버로 올리는 부분을 추가해야함
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("file/*");
                startActivityForResult(intent, 0);
            }
        });

        gather_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //파일만 모아서 볼 수 있는 버튼
                //파일 타입의 리스트뷰 아이템만 보여주게
            }
        });



    }

    private void insertDummy() {

        MemoItem memoItem = new MemoItem();

        memoItem.memo = "memo content";
        memoItem.owner = "업로드한 사람 이름";
        memoItem.file_link = null;
        memoItem.type = 0;
        memoRepo.insert(memoItem);

        memoRepo = new MemoRepo(this);
        memoItem.memo = "file content";
        memoItem.owner = "업로드한 사람 이름";
        memoItem.file_link = null;
        memoItem.type = 1;
        memoRepo.insert(memoItem);


    }

    @Override
    public void onResume(){
        super.onResume();

    }

    //이 부분은 나도 정확한 동작원리를 모르겠어서..
    //아마도 서치 뷰 그리는 부분이라고 생각함
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.options_menu, menu);


        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView search = (SearchView) menu.findItem(R.id.search).getActionView();
            search.setSearchableInfo(manager.getSearchableInfo(getComponentName()));

            search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

                @Override
                public boolean onQueryTextSubmit(String s) {
                    Log.d(TAG, "onQueryTextSubmit ");
                    cursor=memoRepo.getMemoListByKeyword(s);
                    if (cursor==null){
                        Toast.makeText(MemoActivity.this,"No records found!",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MemoActivity.this, cursor.getCount() + " records found!",Toast.LENGTH_LONG).show();
                    }
                    memoAdapter.swapCursor(cursor);

                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    Log.d(TAG, "onQueryTextChange ");
                    cursor=memoRepo.getMemoListByKeyword(s);
                    if (cursor!=null){
                        memoAdapter.swapCursor(cursor);
                    }
                    return false;
                }

            });

        }

        return super.onCreateOptionsMenu(menu);

    }

}
