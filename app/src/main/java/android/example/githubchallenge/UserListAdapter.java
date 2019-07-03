package android.example.githubchallenge;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListAdapterViewHolder> {
    private List<User> mUserData;
    private Context context;

    public UserListAdapter(Context ct){
        this.context = ct;
    }

    public class UserListAdapterViewHolder extends RecyclerView.ViewHolder {
        public final TextView mLoginIdView;
        public final ImageView mAvatarView;

        public UserListAdapterViewHolder (View view){
            super(view);
            mLoginIdView = view.findViewById(R.id.tv_user);
            mAvatarView = view.findViewById(R.id.tv_avatar);
        }
    }

    @Override
    public UserListAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();

        int layoutIdforListItem = R.layout.user_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdforListItem, parent, shouldAttachToParentImmediately);

        return new UserListAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserListAdapterViewHolder holder, int position) {
        User currentUser = mUserData.get(position);
        holder.mLoginIdView.setText(currentUser.getLoginId());

        // use Picasso to download and place image in view holder
//        Picasso.Builder builder = new Picasso.Builder(context);
//        builder.downloader(new OkHttp3Downloader(context));
//        builder.build().load(mUserData.get(position).getAvatarUrl())
//                .placeholder((R.drawable.ic_launcher_background))
//                .error(R.drawable.ic_launcher_background)
//                .into(holder.mAvatarView);

        Picasso.with(context).load(currentUser.getAvatarUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.mAvatarView);

    }

    @Override
    public int getItemCount() {
        if(mUserData == null){
            return 0;
        }
        else{
            return mUserData.size();
        }
    }

    public void setUserData(List<User> userData){
        mUserData = userData;
        notifyDataSetChanged();
    }
}
