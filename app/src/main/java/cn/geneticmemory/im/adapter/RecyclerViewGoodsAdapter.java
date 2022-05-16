package cn.geneticmemory.im.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import cn.geneticmemory.im.R;
import cn.geneticmemory.im.model.Goods;

public class RecyclerViewGoodsAdapter extends RecyclerView.Adapter<RecyclerViewGoodsAdapter.RecyclerHolder> {
    public RecyclerViewGoodsAdapter(List<Goods> dataList) {
        this.datalist = dataList;
    }
    List<Goods> datalist;
    @NonNull
    @NotNull
    @Override
    public RecyclerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.goods_item,parent,false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerHolder holder, int position) {
        holder.avatar.setImageResource(datalist.get(position).getAvatar());
        holder.goods_name.append(datalist.get(position).getGoods_name());
        holder.goods_number.append(datalist.get(position).getGoods_number());
        holder.create_date.append(datalist.get(position).getCreate_date());
        holder.goods_price.append(datalist.get(position).getGoods_price());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.myClick(v,position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                listener.mLongClick(v,position);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public void removeData(int position){
        datalist.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,datalist.size());

    }
    public void  addData(int position,Goods goods){
        datalist.add(position,goods);
        notifyItemInserted(position);
        notifyItemRangeChanged(position,datalist.size());
    }
    public void updateData(int position,Goods goods){
        Collections.replaceAll(datalist,datalist.get(position),goods);
        notifyItemChanged(position);
        notifyItemRangeChanged(position,datalist.size());
    }
    private OnMyItemClickListener listener;
    public void setOnMyItemClickListener(OnMyItemClickListener listener){
        this.listener = listener;

    }

    public interface OnMyItemClickListener{
        void myClick(View v,int pos);
        void mLongClick(View v,int pos);
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView goods_name;
        TextView goods_number;
        TextView create_date;
        TextView goods_price;
        public RecyclerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.avatar = itemView.findViewById(R.id.avatar);
            this.goods_name = itemView.findViewById(R.id.goods_name);
            this.goods_number = itemView.findViewById(R.id.goods_number);
            this.create_date = itemView.findViewById(R.id.create_date);
            this.goods_price = itemView.findViewById(R.id.goods_price);
        }
    }
}
