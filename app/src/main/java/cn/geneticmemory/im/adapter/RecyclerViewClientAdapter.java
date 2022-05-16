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
import cn.geneticmemory.im.model.Client;
import cn.geneticmemory.im.model.Goods;

public class RecyclerViewClientAdapter extends RecyclerView.Adapter<RecyclerViewClientAdapter.RecyclerHolder>{
    private List<Client> datalist;

    public RecyclerViewClientAdapter(List<Client> datalist) {
        this.datalist = datalist;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerViewClientAdapter.RecyclerHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_itme,parent,false);
        return new RecyclerViewClientAdapter.RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerViewClientAdapter.RecyclerHolder holder, int position) {
        holder.avatar.setImageResource(datalist.get(position).getAvatar());
        holder.client_name.append(datalist.get(position).getClient_name());
        holder.client_phone.append(datalist.get(position).getClient_phone());
        holder.client_address.append(datalist.get(position).getClient_address());
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
    private RecyclerViewClientAdapter.OnMyItemClickListener listener;
    public void setOnMyItemClickListener(RecyclerViewClientAdapter.OnMyItemClickListener listener){
        this.listener = listener;

    }

    public interface OnMyItemClickListener{
        void myClick(View v,int pos);
        void mLongClick(View v,int pos);
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
    public void  addData(int position, Client client){
        datalist.add(position,client);
        notifyItemInserted(position);
        notifyItemRangeChanged(position,datalist.size());
    }
    public void updateData(int position,Client client){
        Collections.replaceAll(datalist,datalist.get(position),client);
        notifyItemInserted(position);
        notifyItemRangeChanged(position,datalist.size());
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        ImageView avatar;
        TextView client_name;
        TextView client_phone;
        TextView client_address;
        public RecyclerHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.client_avatar);
            client_name = itemView.findViewById(R.id.client_name);
            client_phone = itemView.findViewById(R.id.client_phone);
            client_address = itemView.findViewById(R.id.client_address);
        }
    }
}
