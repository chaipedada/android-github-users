package android.example.githubchallenge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UserListAdapter adapter;
    private RecyclerView recyclerView;
    private TextView error_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        error_message = (TextView) findViewById(R.id.tv_error_message);
        adapter = new UserListAdapter(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        final Observer<List<User>> updateData = new Observer<List<User>>(){
            @Override
            public void onChanged(List<User> users) {
                adapter.setUserData(users);
                if(users == null){
                    Log.e("UserData", "NULL OBJECT RETURNED FROM CALL");
                    error_message.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.INVISIBLE);
                }
                else{
                    Log.v("Number of Users", Integer.toString(users.size()));
                    recyclerView.setVisibility(View.VISIBLE);
                    error_message.setVisibility(View.INVISIBLE);
                }
            }
        };

        UserDataViewModel model = ViewModelProviders.of(this).get(UserDataViewModel.class);
        model.getLiveData().observe(this, updateData);
        model.loadData();
    }
}
