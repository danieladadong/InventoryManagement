package cn.geneticmemory.im.ui.orders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import cn.geneticmemory.im.R;
import cn.geneticmemory.im.adapter.RecyclerViewGoodsAdapter;
import cn.geneticmemory.im.adapter.RecyclerViewOrdersAdapter;
import cn.geneticmemory.im.model.Goods;
import cn.geneticmemory.im.model.Orders;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersFragment extends Fragment {
    static private List<Orders> datalist;
    private RecyclerView order_recyclerview;
    Orders  result_orders = new Orders();
    private View view;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrdersFragment newInstance(String param1, String param2) {
        OrdersFragment fragment = new OrdersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();
        order_recyclerview = view.findViewById(R.id.order_recyclerview);
        RecyclerViewOrdersAdapter ordersAdapter = new RecyclerViewOrdersAdapter(datalist);
        order_recyclerview.setLayoutManager(new GridLayoutManager(getContext(),1));
        order_recyclerview.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        order_recyclerview.setAdapter(ordersAdapter);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        datalist = new ArrayList<Orders>();
        for(int i=0;i<5;i++){
            Orders orders = new Orders(R.mipmap.jd,"京都念慈庵|东阿阿胶","5件","100元","500元","张三");
            datalist.add(orders);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_orders, container, false);
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("执行onActivityResult");
        if(resultCode== Activity.RESULT_OK) {
            Bundle bundle = data.getExtras();
            result_orders.setOrder_avatar(R.mipmap.jd);
            result_orders.setOrder_client(bundle.get("orderclientname").toString());
            result_orders.setOrder_goods(bundle.get("ordergoods").toString());
            result_orders.setOrder_count(bundle.get("ordercount").toString());
            result_orders.setOrder_price("100元");
            result_orders.setOrder_money(bundle.get("ordermoney").toString());
            result_orders.setCreate_date(bundle.get("ordercreatedate").toString());
        }
        RecyclerViewOrdersAdapter ordersAdapte = new RecyclerViewOrdersAdapter(datalist);
        ordersAdapte.addData(datalist.size(),result_orders);
    }
}