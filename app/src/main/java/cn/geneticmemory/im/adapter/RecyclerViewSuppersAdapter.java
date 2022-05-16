package cn.geneticmemory.im.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

import cn.geneticmemory.im.R;
import cn.geneticmemory.im.model.Goods;
import cn.geneticmemory.im.model.Suppers;

public class RecyclerViewSuppersAdapter extends RecyclerView.Adapter<RecyclerViewSuppersAdapter.RecyclerHolder> {
    private List<Suppers> datalist;

    public RecyclerViewSuppersAdapter(List<Suppers> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerViewSuppersAdapter.RecyclerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.suppers_item,parent,false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerViewSuppersAdapter.RecyclerHolder holder, int position) {
        holder.avatar.setImageResource(datalist.get(position).getAvatar());
        holder.suppers_name.append(datalist.get(position).getSuppers_name());
        holder.suppers_phone.append(datalist.get(position).getSuppers_phone());
        holder.suppers_address.append(datalist.get(position).getSuppers_address());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.myClick(v,position);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
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

    public void  addData(int position, Suppers suppers){
        datalist.add(position,suppers);
        notifyItemInserted(position);
        notifyItemRangeChanged(position,datalist.size());
    }

    public void updateData(int position,Suppers suppers){
        Collections.replaceAll(datalist,datalist.get(position),suppers);
        notifyItemInserted(position);
        notifyItemRangeChanged(position,datalist.size());
    }

    public void removeData(int position){
        datalist.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,datalist.size());

    }
    private RecyclerViewGoodsAdapter.OnMyItemClickListener listener;
    public void setOnMyItemClickListener(RecyclerViewGoodsAdapter.OnMyItemClickListener listener){
        this.listener = listener;

    }

    public interface OnMyItemClickListener{
        void myClick(View v,int pos);
        void mLongClick(View v,int pos);
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView suppers_name;
        TextView suppers_phone;
        TextView suppers_address;
        public RecyclerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.suppers_avatar);
            suppers_name = itemView.findViewById(R.id.suppers_name);
            suppers_phone = itemView.findViewById(R.id.suppers_phone);
            suppers_address = itemView.findViewById(R.id.suppers_address);
        }
    }
}
