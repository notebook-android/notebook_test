package com.example.notebook_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.notebook_test.Adapter.HistoryAdapter;
import com.example.notebook_test.Adapter.SearchAdapter;
import com.example.notebook_test.Model.Note;
import com.quinny898.library.persistentsearch.SearchBox;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {


    private SearchBox search;

    private ArrayList<Note> list_search ;
    private ArrayList<Note> list_result ;

    private String currentName;

    private ListView mSearchResult ;
    private SearchAdapter mResultAdapter ;

    private ListView mHistory ;
    private HistoryAdapter mHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        currentName = getIntent().getStringExtra("currentFolderName");
//        init();
//        initSearchView();
//        initHistory();
   }
}
//
//   initHistory @Override
//    protected void onStart(){
//        super.onStart();
//        init();
//        update_Bottom();
//    }
//
//    public void init(){
//
//        DBManager dbManager = new DBManager(this);
//
//        list_search = dbManager.search(currentName,0,0,0,0);
//        list_result = new ArrayList<>();
//
//        mSearchResult = (ListView) findViewById(R.id.content_lis_search);
//
//        mResultAdapter = new SearchAdapter(SearchActivity.this,list_result);
//
//        mSearchResult.setAdapter(mResultAdapter);
//
//        mSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                NoteManager manager=new NoteManager
//                        (SearchActivity.this,currentName,list_result,mResultAdapter);
//                manager.ItemClick(position);
//            }
//        });
//
//    }
//
//    private void initSearchView(){
//        search = (SearchBox) findViewById(R.id.searchbox);
//        search.enableVoiceRecognition(this);
//
//        search.setMenuListener(new SearchBox.MenuListener(){
//            @Override
//            public void onMenuClick() {
//
//                reBack();
//            }
//        });
//        search.setSearchListener(new SearchBox.SearchListener(){
//
//            @Override
//            public void onSearchOpened() {
//                //Use this to tint the screen
//            }
//            @Override
//            public void onSearchClosed() {
//                //Use this to un-tint the screen
//            }
//            @Override
//            public void onSearchTermChanged(String term) {
//                search(term);
//                update_Bottom();
//
//                if(list_result.size()==0){
//                    mHistory.setVisibility(View.VISIBLE);
//                }else {
//                    mHistory.setVisibility(View.GONE);
//                }
//            }
//            @Override
//            public void onSearch(String searchTerm) {
//                search(searchTerm);
//                saveHistory(searchTerm);
//                initHistory();
//                update_Bottom();
//            }
//            @Override
//            public void onResultClick(SearchResult result) {
//                //React to a result being clicked
//            }
//            @Override
//            public void onSearchCleared() {
//                //Called when the clear button is clicked
//            }
//
//        });
//
//        //search.setOverflowMenu(R.menu.)
//    }
//
//    private void initHistory(){
//
//        final List<String> history= getHistory();
//
//        mHistory = (ListView) findViewById(R.id.history_lis_search);
//        mHistoryAdapter = new HistoryAdapter(SearchActivity.this,history);
//
//        mHistory.setAdapter(mHistoryAdapter);
//
//        mHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                search.populateEditText(history.get(position));
//                search.setSearchString(history.get(position));
//
//            }
//        });
//
//    }
//
//    private void search(String newText){
//
//        //若搜索内容为空
//        if(newText.isEmpty()){
//            list_result.clear();
//        }
//        else{
//            for (Note note : list_search) {//开始搜索
//                //搜索内容搜索到相关
//                if (note.getName().contains(newText) || note.getText().contains(newText)) {
//
//                    if(list_result.indexOf(note)==-1) {//且 结果集内不含有此内容
//                        list_result.add(note);
//                    }
//                }else{
//                    //搜索内容搜索不到相关 检测是否之前有加入结果集 有则删除
//                    if(list_result.indexOf(note)!=-1) {
//                        list_result.remove(note);
//                    }
//                }
//            }
//        }
//        mResultAdapter.notifyDataSetChanged();
//    }
//
//    /**
//     * 获取历史搜索记录
//     * @return
//     */
//    private ArrayList<String> getHistory() {
//
//        SharedPreferences reader = getSharedPreferences("history", MODE_PRIVATE);
//
//        String data = reader.getString("data_history", "");
//
//        ArrayList<String> history = new ArrayList<>();
//
//        String [] get=  data.split("\\|");
//
//        for( String str:get){
//            if(! history.contains(str) && !StringUtil.isEmpty(str)){
//                history.add(str);
//            }
//        }
//
//        return history;
//    }
//
//    /**
//     * 保存搜索到历史记录
//     * @param s
//     */
//    private void saveHistory(String s){
//
//        StringBuilder sb = new StringBuilder();
//
//        SharedPreferences.Editor editor = getSharedPreferences("history",MODE_PRIVATE).edit();
//
//        for (String str: getHistory()){
//            sb.append(str);
//            sb.append("|");
//        }
//
//        sb.append(s);
//        editor.putString("data_history",sb.toString());
//        editor.apply();
//    }
//
//
//    private void update_Bottom(){
//
//        TextView bottom = (TextView) findViewById(R.id.bottom_search);
//
//        if(search.getSearchText().trim().isEmpty()){
//            gone_Bottom();
//            return;
//        }
//
//        // mHistory.setVisibility(View.GONE);
//
//        bottom.setVisibility(View.VISIBLE);
//        bottom.setText("找到了 "+list_result.size() +" 条记录");
//    }
//    private void gone_Bottom(){
//
//        TextView bottom = (TextView) findViewById(R.id.bottom_search);
//        bottom.setVisibility(View.GONE);
//    }
//
//    private void reBack(){
//        finish();
//        overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
//    }
//
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//
//        switch(keyCode){
//            case KeyEvent.KEYCODE_BACK:
//                finish();
//                overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right);
//                break;
//        }
//        return super.onKeyUp(keyCode, event);
//    }
//
//}
