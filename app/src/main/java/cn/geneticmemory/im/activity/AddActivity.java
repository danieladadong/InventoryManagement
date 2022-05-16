package cn.geneticmemory.im.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import cn.geneticmemory.im.R;
import cn.geneticmemory.im.adapter.ListViewOrderAdapter;
import cn.geneticmemory.im.model.Orders;

public class AddActivity extends AppCompatActivity {
    private static ListViewOrderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        switch (bundle.getString("tag")){
            case "goods":
                setContentView(R.layout.activity_addgoods);
                this.getSupportActionBar().setTitle("新建商品");
                initGoodsView();
                break;
            case "client":
                setContentView(R.layout.activity_addclient);
                this.getSupportActionBar().setTitle("新建客户");
                initClientView();
                break;
            case "orders":
                setContentView(R.layout.activity_addorders);
                this.getSupportActionBar().setTitle("新建订单");
                initOrdersView();
                break;
            case "suppers":
                setContentView(R.layout.activity_addsuppers);
                this.getSupportActionBar().setTitle("新建供货商");
                initSuppersView();
                break;
        }

    }
    public void initGoodsView(){
        EditText goodsname = findViewById(R.id.goodsname);
        EditText goodsnumber = findViewById(R.id.goodsnumber);
        EditText goodssuppers = findViewById(R.id.goodssuppers);
        EditText goodsprice = findViewById(R.id.goodsprice);
        EditText goodscreatedate = findViewById(R.id.goodscreatedate);
        Button save_goods = findViewById(R.id.save_goods);
        save_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("goodsname",goodsname.getText().toString());
                bundle.putString("goodsnumber",goodsnumber.getText().toString());
                bundle.putString("goodssuppers",goodssuppers.getText().toString());
                bundle.putString("goodsprice",goodsprice.getText().toString());
                bundle.putString("goodscreatedate",goodscreatedate.getText().toString());
                intent.putExtras(bundle);
                AddActivity.this.setResult(Activity.RESULT_OK,intent);
                AddActivity.this.finish();
            }
        });
    }
    public void initClientView(){
        EditText clientname = findViewById(R.id.clientname);
        EditText clientphone = findViewById(R.id.clientphone);
        EditText clientaddress = findViewById(R.id.clientaddress);
        EditText clientlast = findViewById(R.id.clientlast);
        EditText clientcreatedate = findViewById(R.id.clientcreatedate);
        Button save_client = findViewById(R.id.save_client);
        save_client.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("clientname",clientname.getText().toString());
                bundle.putString("clientphone",clientphone.getText().toString());
                bundle.putString("clientaddress",clientaddress.getText().toString());
                bundle.putString("clientlast",clientlast.getText().toString());
                bundle.putString("clientcreatedate",clientcreatedate.getText().toString());
                intent.putExtras(bundle);
                AddActivity.this.setResult(Activity.RESULT_OK,intent);
                AddActivity.this.finish();
            }
        });
    }
    public void initOrdersView(){
        EditText order_client_name = findViewById(R.id.order_client_name);
        ImageButton choose_client_name = findViewById(R.id.choose_client_name);
        ImageButton choose_goods = findViewById(R.id.choose_goods);
        ListView order_list = findViewById(R.id.order_list);
        TextView order_count = findViewById(R.id.order_count);
        TextView order_money = findViewById(R.id.order_money);
        TextView order_create_date = findViewById(R.id.order_create_date);
        ImageButton choose_date = findViewById(R.id.choose_date);
        Button save_order = findViewById(R.id.save_order);
        choose_client_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] item = {"张三", "李四", "王五"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);//得到构造器
                builder.setTitle("请选择客户名");
                builder.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        order_client_name.setText(item[which]);
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        List<Orders> datlist = new ArrayList<Orders>();
        adapter = new ListViewOrderAdapter(datlist,getLayoutInflater());
        choose_goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] item = {"京都念慈庵", "六味地黄丸", "乌鸡白凤丸"};
                final AlertDialog.Builder builder = new AlertDialog.Builder(AddActivity.this);//得到构造器
                builder.setTitle("请选择商品");
                builder.setItems(item, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Orders orders = new Orders();
                        orders.setOrder_goods(item[which]);
                        orders.setOrder_count("1");
                        adapter.add(orders);
                        order_list.setAdapter(adapter);
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });
        order_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = 0;
                for(int i=0;i<adapter.getCount();i++){
                    TextView textView = order_list.getAdapter().getView(i,null,null).findViewById(R.id.number);
                    int tmp = Integer.parseInt(textView.getText().toString());
                    count+=tmp;
                }
                order_count.setText(""+count);
            }
        });
        order_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (order_count.getText().toString()==null||order_count.getText().toString()==""){
                    Toast.makeText(AddActivity.this,"请先点击数量", Toast.LENGTH_SHORT).show();
                }else{
                    order_money.setText(""+100*Integer.parseInt(order_count.getText().toString()));
                }
            }
        });
        choose_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance(Locale.CHINA);
                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, 0, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        order_create_date.setText(year+"-"+month+"-"+dayOfMonth);
                    }
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        save_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("orderclientname",order_client_name.getText().toString());
                String goods = "";
                for(int i=0;i<adapter.getCount();i++){
                    TextView textView = order_list.getAdapter().getView(i,null,null).findViewById(R.id.add_order_goods);
                    String tmp = textView.getText().toString();
                    goods = tmp+"|";
                }
                bundle.putString("ordergoods",goods);
                bundle.putString("ordercount",order_count.getText().toString());
                bundle.putString("ordermoney",order_money.getText().toString());
                bundle.putString("ordercreatedate",order_create_date.getText().toString());
                intent.putExtras(bundle);
                AddActivity.this.setResult(Activity.RESULT_OK,intent);
                AddActivity.this.finish();
            }
        });

    }
    public void initSuppersView(){
        EditText suppersname = findViewById(R.id.suppersname);
        EditText suppersphone = findViewById(R.id.suppersphone);
        EditText suppersaddress = findViewById(R.id.suppersaddress);
        EditText supperslast = findViewById(R.id.supperslast);
        EditText supperscreatedate = findViewById(R.id.supperscreatedate);
        Button save_suppers = findViewById(R.id.save_suppers);
        save_suppers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("suppersname",suppersname.getText().toString());
                bundle.putString("suppersphone",suppersphone.getText().toString());
                bundle.putString("suppersaddress",suppersaddress.getText().toString());
                bundle.putString("supperslast",supperslast.getText().toString());
                bundle.putString("supperscreatedate",supperscreatedate.getText().toString());
                intent.putExtras(bundle);
                AddActivity.this.setResult(Activity.RESULT_OK,intent);
                AddActivity.this.finish();
            }
        });
    }
}