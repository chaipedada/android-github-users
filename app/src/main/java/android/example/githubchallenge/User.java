package android.example.githubchallenge;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("login")
    private String loginId;
    @SerializedName("avatar_url")
    private String avatarUrl;

    public User(String loginId, String avatarUrl){
        this.loginId = loginId;
        this.avatarUrl = avatarUrl;
    }

    public String getLoginId(){
        return loginId;
    }

    public String getAvatarUrl(){
        return avatarUrl;
    }
}
