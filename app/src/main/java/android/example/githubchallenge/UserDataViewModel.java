package android.example.githubchallenge;

import android.example.githubchallenge.utilities.GithubService;
import android.example.githubchallenge.utilities.NetworkUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;

    public UserDataViewModel() {
        users = new MutableLiveData<List<User>>();
    }

    public void loadData() {
        if (users.getValue() == null){
            // Create retrofit instance and call to get user list
            GithubService service = NetworkUtils.getRetrofitInstance().create(GithubService.class);
            Call<List<User>> call = service.listUsers();
            call.enqueue(new Callback<List<User>>() {
                @Override
                public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                    users.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<User>> call, Throwable t) {
                    users.setValue(null);
                }
            });
        }
    }

    public LiveData<List<User>> getLiveData(){
        return users;
    }

}
