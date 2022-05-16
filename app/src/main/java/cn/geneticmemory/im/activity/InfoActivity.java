package cn.geneticmemory.im.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.geneticmemory.im.R;

public class InfoActivity extends AppCompatActivity {
    private TextView text_1;
    private TextView text_2;
    private TextView text_3;
    private TextView text_4;
    private TextView text_5;
    private EditText info_1;
    private EditText info_2;
    private EditText info_3;
    private EditText info_4;
    private EditText info_5;
    private Button update_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        initTextView();
        switch (bundle.getString("tag")){
            case "goods":
                this.getSupportActionBar().setTitle("商品详情");
                initGoods(bundle);
                break;
            case "client":
                this.getSupportActionBar().setTitle("客户详情");
                initClient(bundle);
                break;
            case "suppers":
                this.getSupportActionBar().setTitle("供货商详情");
                initSuppers(bundle);
                break;
        }

    }
    public void initTextView(){
        text_1 = findViewById(R.id.text_1);
        text_2 = findViewById(R.id.text_2);
        text_3 = findViewById(R.id.text_3);
        text_4 = findViewById(R.id.text_4);
        text_5 = findViewById(R.id.text_5);
        info_1 = findViewById(R.id.info_1);
        info_2 = findViewById(R.id.info_2);
        info_3 = findViewById(R.id.info_3);
        info_4 = findViewById(R.id.info_4);
        info_5 = findViewById(R.id.info_5);
        update_info = findViewById(R.id.update_info);
    }
    public void initGoods(Bundle bundle){
        text_1.setText("商品名称：");
        text_2.setText("当前库存：");
        text_3.setText("供货商：");
        text_4.setText("价格：");
        text_5.setText("创建时间：");
        info_1.setText(bundle.getString("name"));
        info_2.setText(bundle.getString("number"));
        info_3.setText(bundle.getString("suppers"));
        info_4.setText(bundle.getString("price"));
        info_5.setText(bundle.getString("createdate"));
        update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("name",info_1.getText().toString());
                bundle.putString("number",info_2.getText().toString());
                bundle.putString("suppers",info_3.getText().toString());
                bundle.putString("price",info_4.getText().toString());
                bundle.putString("createdate",info_5.getText().toString());
                intent.putExtras(bundle);
                InfoActivity.this.setResult(Activity.RESULT_OK,intent);
                InfoActivity.this.finish();
            }
        });
    }
    public void initClient(Bundle bundle){
        text_1.setText("客户名称：");
        text_2.setText("电话：");
        text_3.setText("地址：");
        text_4.setText("上次拿货时间：");
        text_5.setText("创建时间：");
        info_1.setText(bundle.getString("name"));
        info_2.setText(bundle.getString("phone"));
        info_3.setText(bundle.getString("address"));
        info_4.setText(bundle.getString("last"));
        info_5.setText(bundle.getString("createdate"));
        update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("name",info_1.getText().toString());
                bundle.putString("phone",info_2.getText().toString());
                bundle.putString("address",info_3.getText().toString());
                bundle.putString("last",info_4.getText().toString());
                bundle.putString("createdate",info_5.getText().toString());
                intent.putExtras(bundle);
                InfoActivity.this.setResult(Activity.RESULT_OK,intent);
                InfoActivity.this.finish();
            }
        });
    }
    public void initSuppers(Bundle bundle){
        text_1.setText("客户名称：");
        text_2.setText("电话：");
        text_3.setText("地址：");
        text_4.setText("上次拿货时间：");
        text_5.setText("创建时间：");
        info_1.setText(bundle.getString("name"));
        info_2.setText(bundle.getString("phone"));
        info_3.setText(bundle.getString("address"));
        info_4.setText(bundle.getString("last"));
        info_5.setText(bundle.getString("createdate"));
        update_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("name",info_1.getText().toString());
                bundle.putString("phone",info_2.getText().toString());
                bundle.putString("address",info_3.getText().toString());
                bundle.putString("last",info_4.getText().toString());
                bundle.putString("createdate",info_5.getText().toString());
                intent.putExtras(bundle);
                InfoActivity.this.setResult(Activity.RESULT_OK,intent);
                InfoActivity.this.finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        InfoActivity.this.setResult(Activity.RESULT_CANCELED);
        InfoActivity.this.finish();
    }
}