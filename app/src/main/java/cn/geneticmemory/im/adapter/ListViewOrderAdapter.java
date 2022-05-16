package cn.geneticmemory.im.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeUtils;

import java.util.ArrayList;
import java.util.List;

import cn.geneticmemory.im.R;
import cn.geneticmemory.im.activity.AddActivity;
import cn.geneticmemory.im.model.Orders;

public class ListViewOrderAdapter extends BaseAdapter {
    private List<Orders> datalist;
    private LayoutInflater inflater;

    public ListViewOrderAdapter(List<Orders> datalist,LayoutInflater inflater) {
        this.datalist = datalist;
        this.inflater = inflater;
    }

    public ListViewOrderAdapter() {
    }

    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(Orders orders) {
        if (datalist == null) {
           datalist = new ArrayList<Orders>();
        }
        datalist.add(orders);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.add_order_item,null);
        Button sub  = view.findViewById(R.id.sub);
        Button add = view.findViewById(R.id.add);
        TextView number = view.findViewById(R.id.number);
        TextView order_goods = view.findViewById(R.id.add_order_goods);
        TextView order_count = view.findViewById(R.id.number);
        order_goods.setText(datalist.get(position).getOrder_goods());
        order_count.setText(datalist.get(position).getOrder_count());
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(number.getText().toString());
                if(num>1){
                    num = num-1;
                    datalist.get(position).setOrder_count(""+num);
                    number.setText(""+num);

                }else {
                    Toast.makeText(parent.getContext(),"数量不能低于1",Toast.LENGTH_SHORT).show();
                }
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int num = Integer.parseInt(number.getText().toString());
                num = num+1;
                datalist.get(position).setOrder_count(""+num);
                number.setText(""+num);
            }
        });
        return view;
    }
}
