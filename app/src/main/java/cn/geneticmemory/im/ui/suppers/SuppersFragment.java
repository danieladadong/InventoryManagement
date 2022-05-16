package cn.geneticmemory.im.ui.suppers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.geneticmemory.im.R;
import cn.geneticmemory.im.activity.InfoActivity;
import cn.geneticmemory.im.adapter.RecyclerViewGoodsAdapter;
import cn.geneticmemory.im.adapter.RecyclerViewOrdersAdapter;
import cn.geneticmemory.im.adapter.RecyclerViewSuppersAdapter;
import cn.geneticmemory.im.model.Suppers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SuppersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SuppersFragment extends Fragment {
    static private List<Suppers> datalist;
    private RecyclerView suppers_recyclerview;
    private View view;
    Suppers result_suppers = new Suppers();
    static private int posi;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SuppersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SuppersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SuppersFragment newInstance(String param1, String param2) {
        SuppersFragment fragment = new SuppersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        suppers_recyclerview = view.findViewById(R.id.suppers_recyclerview);
        RecyclerViewSuppersAdapter suppersAdapter = new RecyclerViewSuppersAdapter(datalist);
        suppersAdapter.setOnMyItemClickListener(new RecyclerViewGoodsAdapter.OnMyItemClickListener() {
            @Override
            public void myClick(View v, int pos) {
                posi = pos;
                Intent intent = new Intent(v.getContext(), InfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tag","suppers");
                bundle.putString("name",datalist.get(pos).getSuppers_name());
                bundle.putString("phone",datalist.get(pos).getSuppers_phone());
                bundle.putString("address",datalist.get(pos).getSuppers_address());
                bundle.putString("last",datalist.get(pos).getLast_time());
                bundle.putString("createdate",datalist.get(pos).getCreate_date());
                intent.putExtras(bundle);
                startActivityForResult(intent,124);
            }

            @Override
            public void mLongClick(View v, int pos) {
                operationMenu(v,suppersAdapter,pos);
            }
        });
        suppers_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),1));
        suppers_recyclerview.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        suppers_recyclerview.setAdapter(suppersAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        datalist = new ArrayList<Suppers>();
        for(int i=0;i<5;i++){
            Suppers suppers = new Suppers(R.mipmap.clients,"张三","18560752226","山东济南","2021-1-5","2020-10-5");
            datalist.add(suppers);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_suppers, container, false);
        return view;
    }
    private void operationMenu(View v,RecyclerViewSuppersAdapter suppersAdapter,int position){
        PopupMenu menu = new PopupMenu(v.getContext(),v);
        menu.getMenuInflater().inflate(R.menu.operation_menu,menu.getMenu());
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getTitle()=="修改信息"){
                }else{
                    AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                    dialog.setIcon(R.mipmap.ic_launcher_round);
                    dialog.setTitle("提示！");
                    dialog.setMessage("是否删除这条信息？");
                    dialog.setCancelable(false);    //设置是否可以通过点击对话框外区域或者返回按键关闭对话框
                    dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            suppersAdapter.removeData(position);
                            Toast.makeText(v.getContext(), "删除成功！", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(v.getContext(), "取消删除", Toast.LENGTH_SHORT).show();
                        }
                    });
                    dialog.show();
                }
                return true;
            }
        });
        menu.show();
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("执行onActivityResult");
        RecyclerViewSuppersAdapter suppersAdapter = new RecyclerViewSuppersAdapter(datalist);
        switch (requestCode){
            case 0x11:
                if(resultCode== Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    result_suppers.setAvatar(R.mipmap.clients);
                    result_suppers.setSuppers_name(bundle.get("suppersname").toString());
                    result_suppers.setSuppers_phone(bundle.get("suppersphone").toString());
                    result_suppers.setSuppers_address(bundle.get("suppersaddress").toString());
                    result_suppers.setLast_time(bundle.get("supperslast").toString());
                    result_suppers.setCreate_date(bundle.get("supperscreatedate").toString());
                }
                suppersAdapter.addData(datalist.size(),result_suppers);
                break;
            case 124:
                if(resultCode== Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    result_suppers.setAvatar(R.mipmap.clients);
                    result_suppers.setSuppers_name(bundle.get("name").toString());
                    result_suppers.setSuppers_phone(bundle.get("phone").toString());
                    result_suppers.setSuppers_address(bundle.get("address").toString());
                    result_suppers.setLast_time(bundle.get("last").toString());
                    result_suppers.setCreate_date(bundle.get("createdate").toString());
                    suppersAdapter.updateData(posi,result_suppers);
                }

        }

    }
}