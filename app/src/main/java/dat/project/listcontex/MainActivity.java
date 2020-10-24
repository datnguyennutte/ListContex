package dat.project.listcontex;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ListViewBaseAdapter adapter;
    ArrayList<ListViewBean> arr_bean;
    Vector<Integer> index=new Vector<Integer>();
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnThem = findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arr_bean.add(new ListViewBean(R.drawable.anh1, "Rượu vang","7.000.000 VNĐ"));
                arr_bean.add(new ListViewBean(R.drawable.anh1, "Rượu vang","7.000.000 VNĐ"));
                arr_bean.add(new ListViewBean(R.drawable.anh2, "Wisky","250.000 VNĐ"));
                adapter.notifyDataSetChanged();
            }
        });
        //Listview
        lv = (ListView) findViewById(R.id.listView);
        arr_bean=new ArrayList<ListViewBean>();
        arr_bean.add(new ListViewBean(R.drawable.anh1, "Rượu vang","7.000.000 VNĐ"));
        arr_bean.add(new ListViewBean(R.drawable.anh2, "Wisky","250.000 VNĐ"));
        arr_bean.add(new ListViewBean(R.drawable.anh3, "Gà luộc","300.000 VNĐ"));
        arr_bean.add(new ListViewBean(R.drawable.anh4, "Cua hoàng đế","2.000.000 VNĐ"));
        arr_bean.add(new ListViewBean(R.drawable.thanbo, "Thăn bò","800.000 VNĐ"));
        arr_bean.add(new ListViewBean(R.drawable.suon, "Sườn heo","1.000.000 VNĐ"));
        arr_bean.add(new ListViewBean(R.drawable.anh1, "Rượu vang","4.000.000 VNĐ"));
        adapter=new ListViewBaseAdapter(arr_bean,this);
        lv.setAdapter((ListAdapter) adapter);
        registerForContextMenu(lv);
        index.add(-1);
        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lv.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                try {
                    //chuẩn bị danh sách các phần tử được chọn
                    if (index.get(0) == -1)
                        index.set(0, position);
                    else {
                        int yn = 0, vt = -1;
                        for (int i = 0; i < index.size(); ++i) {
                            if (index.get(i) == position) {
                                yn = 1;
                                vt = i;
                                break;
                            }
                        }
                        //nếu phần tử đã chọn trc đó thì xóa khỏi danh sách
                        if (yn == 1) index.remove(vt);
                        else index.add(position);
                        adapter.notifyDataSetChanged();
                    }
                }
                catch (Exception e){
                    index.add(position);
                }

                //thiết lập lại màu cho toàn bộ
                for (int i = 0; i < lv.getChildCount(); i++) {
                    lv.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                }
                //thiết lập màu cho các phần tử đc chọn
                for (int i = 0; i < lv.getChildCount(); i++) {
                    for(int j=0;j<index.size();++j)
                        if(index.get(j) == i){
                            lv.getChildAt(i).setBackgroundColor(Color.GRAY);
                        }
                }
                mode.setTitle(index.size()+" đã chọn");

            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                    case android.R.id.home:
                        index.clear();
                        //thiết lập lại màu cho toàn bộ
                        for (int i = 0; i < lv.getChildCount(); i++) {
                            lv.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        }
                        return true;
                    case R.id.them:
                        Toast.makeText(MainActivity.this,"Thêm",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.sua:
                        Toast.makeText(MainActivity.this,"Sửa",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.xoa:
                        try {
                            Collections.sort(index);
                            for (int i = index.size()-1; i >=0 ; --i) {
                                arr_bean.remove(Integer.parseInt(index.get(i).toString()));
                                adapter.notifyDataSetChanged();
                            }
                        }
                        catch (Exception e){
                            Toast.makeText(MainActivity.this,"Bạn chưa chọn dòng nào cả",Toast.LENGTH_SHORT).show();
                        }

                        index.clear();
                        //thiết lập lại màu cho toàn bộ
                        for (int i = 0; i < lv.getChildCount(); i++) {
                            lv.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                        }
                        Log.d("Xoa mang",Integer.toString(index.size()));
                        mode.finish(); // Action picked, so close the CAB
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu, menu);
                return true;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // Here you can make any necessary updates to the activity when
                // the CAB is removed. By default, selected items are deselected/unchecked.
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
            }
        });
    }
}