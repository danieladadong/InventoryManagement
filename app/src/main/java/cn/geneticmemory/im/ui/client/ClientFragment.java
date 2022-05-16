package cn.geneticmemory.im.ui.client;

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
import cn.geneticmemory.im.adapter.RecyclerViewClientAdapter;
import cn.geneticmemory.im.adapter.RecyclerViewGoodsAdapter;
import cn.geneticmemory.im.model.Client;
import cn.geneticmemory.im.model.Goods;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClientFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClientFragment extends Fragment {
    static private List<Client> datalist;
    private RecyclerView client_recyclerview;
    Client result_client = new Client();
    private View view;
    static private int posi;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClientFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClientFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClientFragment newInstance(String param1, String param2) {
        ClientFragment fragment = new ClientFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        client_recyclerview = view.findViewById(R.id.client_recyclerview);
        RecyclerViewClientAdapter clientAdapter = new RecyclerViewClientAdapter(datalist);
        clientAdapter.setOnMyItemClickListener(new RecyclerViewClientAdapter.OnMyItemClickListener() {
            @Override
            public void myClick(View v, int pos) {
                posi = pos;
                Intent intent = new Intent(v.getContext(), InfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("tag","client");
                bundle.putString("name",datalist.get(pos).getClient_name());
                bundle.putString("phone",datalist.get(pos).getClient_phone());
                bundle.putString("address",datalist.get(pos).getClient_address());
                bundle.putString("last",datalist.get(pos).getLast_time());
                bundle.putString("createdate",datalist.get(pos).getCreate_date());
                intent.putExtras(bundle);
                startActivityForResult(intent,122);
            }

            @Override
            public void mLongClick(View v, int pos) {
                operationMenu(v,clientAdapter,pos);
            }
        });
        client_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),1));
        client_recyclerview.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        client_recyclerview.setAdapter(clientAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        datalist = new ArrayList<Client>();
        for(int i=0;i<5;i++){
            Client client = new Client(R.mipmap.clients,"张三","18560752226","山东济南","2021-5-1","2021-4-2");
            datalist.add(client);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_client, container, false);
        return view;
    }
    private void operationMenu(View v, RecyclerViewClientAdapter clientAdapter, int position){
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
                            clientAdapter.removeData(position);
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
        RecyclerViewClientAdapter clientAdapter = new RecyclerViewClientAdapter(datalist);
        switch (requestCode){
            case 0x01:
                if(resultCode== Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    result_client.setAvatar(R.mipmap.clients);
                    result_client.setClient_name(bundle.get("clientname").toString());
                    result_client.setClient_phone(bundle.get("clientphone").toString());
                    result_client.setClient_address(bundle.get("clientaddress").toString());
                    result_client.setCreate_date(bundle.get("clientlast").toString());
                    result_client.setLast_time(bundle.get("clientcreatedate").toString());
                }
                clientAdapter.addData(datalist.size(),result_client);
                break;
            case 122:
                if(resultCode== Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    result_client.setAvatar(R.mipmap.clients);
                    result_client.setClient_name(bundle.get("name").toString());
                    result_client.setClient_phone(bundle.get("phone").toString());
                    result_client.setClient_address(bundle.get("address").toString());
                    result_client.setCreate_date(bundle.get("last").toString());
                    result_client.setLast_time(bundle.get("createdate").toString());
                    clientAdapter.updateData(posi,result_client);
                }
                break;
        }

    }
}