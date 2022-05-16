package cn.geneticmemory.im.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import cn.geneticmemory.im.R;
import cn.geneticmemory.im.model.Orders;
import cn.geneticmemory.im.model.Suppers;

public class RecyclerViewOrdersAdapter extends RecyclerView.Adapter<RecyclerViewOrdersAdapter.RecyclerHolder> {
    private List<Orders> datalist;

    public RecyclerViewOrdersAdapter(List<Orders> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerViewOrdersAdapter.RecyclerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_item,parent,false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerViewOrdersAdapter.RecyclerHolder holder, int position) {
        holder.order_avatar.setImageResource(datalist.get(position).getOrder_avatar());
        holder.order_goods_name.append(datalist.get(position).getOrder_goods());
        holder.order_goods_count.append(datalist.get(position).getOrder_count());
        holder.order_price.append(datalist.get(position).getOrder_price());
        holder.order_goods_money.append(datalist.get(position).getOrder_money());
        holder.order_name.append(datalist.get(position).getOrder_client());
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public void  addData(int position, Orders orders){
        datalist.add(position,orders);
        notifyItemInserted(position);
        notifyItemRangeChanged(position,datalist.size());
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        ImageView order_avatar;
        TextView order_goods_name;
        TextView order_goods_count;
        TextView order_price;
        TextView order_goods_money;
        TextView order_name;

        public RecyclerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            order_avatar = itemView.findViewById(R.id.order_avatar);
            order_goods_name = itemView.findViewById(R.id.order_goods_name);
            order_goods_count = itemView.findViewById(R.id.order_goods_count);
            order_price = itemView.findViewById(R.id.order_price);
            order_goods_money = itemView.findViewById(R.id.order_goods_money);
            order_name = itemView.findViewById(R.id.order_name);
        }
    }
}
