package android.example.githubchallenge.utilities;

import android.example.githubchallenge.User;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GithubService {
    @GET("/users")
    Call<List<User>> listUsers();
}
