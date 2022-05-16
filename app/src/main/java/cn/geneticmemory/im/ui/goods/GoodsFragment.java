package cn.geneticmemory.im.ui.goods;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.PopupMenu;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.geneticmemory.im.R;
import cn.geneticmemory.im.activity.InfoActivity;
import cn.geneticmemory.im.adapter.RecyclerViewGoodsAdapter;
import cn.geneticmemory.im.model.Goods;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GoodsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GoodsFragment extends Fragment {
    static List<Goods> datalist;
    private RecyclerView goods_recyclerview;
    private View view;
    Goods result_goods = new Goods();
    static int posi;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GoodsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GoodsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GoodsFragment newInstance(String param1, String param2) {
        GoodsFragment fragment = new GoodsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        datalist = new ArrayList<Goods>();
        for(int i=0;i<5;i++){
            Goods goods = new Goods(R.mipmap.jd,"京都念慈庵","10","100.0","2021-5-14","张三");
            datalist.add(goods);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("执行onStart");
        for(int i=0;i<datalist.size();i++){
            System.out.println(datalist.get(i).getGoods_name()+i);
        }
        goods_recyclerview = view.findViewById(R.id.goods_recyclerview);
        RecyclerViewGoodsAdapter goodsAdapter = new RecyclerViewGoodsAdapter(datalist);
        goodsAdapter.setOnMyItemClickListener(new RecyclerViewGoodsAdapter.OnMyItemClickListener() {
            @Override
            public void myClick(View v, int pos) {
                System.out.println(pos);
                posi=pos;
                Intent intent = new Intent(v.getContext(), InfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tag","goods");
                bundle.putString("name",datalist.get(pos).getGoods_name());
                bundle.putString("suppers",datalist.get(pos).getGoods_suppers());
                bundle.putString("number",datalist.get(pos).getGoods_number());
                bundle.putString("price",datalist.get(pos).getGoods_price());
                bundle.putString("createdate",datalist.get(pos).getCreate_date());
                intent.putExtras(bundle);
                startActivityForResult(intent,121);
            }

            @Override
            public void mLongClick(View v, int pos) {
                operationMenu(v,goodsAdapter,pos);
            }
        });
        goods_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),1));
        goods_recyclerview.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        goods_recyclerview.setAdapter(goodsAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_goods, container, false);
        return view;
    }
    private void operationMenu(View v,RecyclerViewGoodsAdapter goodsAdapter,int position){
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
                            goodsAdapter.removeData(position);
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
        RecyclerViewGoodsAdapter goodsAdapter = new RecyclerViewGoodsAdapter(datalist);
        switch (requestCode){
            case 0x00:
                if(resultCode== Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    result_goods.setAvatar(R.mipmap.jd);
                    result_goods.setGoods_name(bundle.get("goodsname").toString());
                    result_goods.setGoods_number(bundle.get("goodsnumber").toString());
                    result_goods.setGoods_price(bundle.get("goodsprice").toString());
                    result_goods.setCreate_date(bundle.get("goodscreatedate").toString());
                    result_goods.setGoods_suppers(bundle.get("goodssuppers").toString());
                }
                goodsAdapter.addData(datalist.size(),result_goods);
                break;
            case 121:
                if(resultCode== Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    result_goods.setAvatar(R.mipmap.jd);
                    result_goods.setGoods_name(bundle.get("name").toString());
                    result_goods.setGoods_number(bundle.get("number").toString());
                    result_goods.setGoods_suppers(bundle.get("suppers").toString());
                    result_goods.setGoods_price(bundle.get("price").toString());
                    result_goods.setCreate_date(bundle.get("createdate").toString());
                    goodsAdapter.updateData(posi,result_goods);
                }

                break;
        }

    }
}