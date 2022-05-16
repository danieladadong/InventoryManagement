package cn.geneticmemory.im.activity;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import cn.geneticmemory.im.R;
import cn.geneticmemory.im.adapter.RecyclerViewGoodsAdapter;
import cn.geneticmemory.im.databinding.ActivityMainBinding;
import cn.geneticmemory.im.model.Goods;
import cn.geneticmemory.im.ui.client.ClientFragment;
import cn.geneticmemory.im.ui.goods.GoodsFragment;
import cn.geneticmemory.im.ui.orders.OrdersFragment;
import cn.geneticmemory.im.ui.suppers.SuppersFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private View fragment_view;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setLabelVisibilityMode(1);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_goods, R.id.navigation_client, R.id.navigation_orders, R.id.navigation_suppers)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(MainActivity.this,AddActivity.class);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        Bundle bundle = new Bundle();
        switch (navView.getSelectedItemId()){
            case R.id.navigation_goods:
                bundle.putString("tag","goods");
                intent.putExtras(bundle);
                startActivityForResult(intent,0x00,bundle);
                return  true;
            case R.id.navigation_client:
                bundle.putString("tag","client");
                intent.putExtras(bundle);
                startActivityForResult(intent,0x01,bundle);
                return true;
            case R.id.navigation_orders:
                bundle.putString("tag","orders");
                intent.putExtras(bundle);
                startActivityForResult(intent,0x10,bundle);
                return true;
            case R.id.navigation_suppers:
                bundle.putString("tag","suppers");
                intent.putExtras(bundle);
                startActivityForResult(intent,0x11,bundle);
                return true;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(requestCode);
        switch (requestCode){
            case 0x00:
                if (resultCode==Activity.RESULT_OK){
                    GoodsFragment fragment = new GoodsFragment();
                    fragment.onActivityResult(requestCode,resultCode,data);
                }
                break;
            case 0x01:
                if (resultCode==Activity.RESULT_OK){
                    ClientFragment fragment = new ClientFragment();
                    fragment.onActivityResult(requestCode,resultCode,data);
                }
                break;
            case 0x10:
                if (resultCode==Activity.RESULT_OK){
                    OrdersFragment fragment = new OrdersFragment();
                    fragment.onActivityResult(requestCode,resultCode,data);
                }
                break;
            case 0x11:
                if (resultCode==Activity.RESULT_OK){
                    SuppersFragment fragment = new SuppersFragment();
                    fragment.onActivityResult(requestCode,resultCode,data);
                }


        }
    }
}