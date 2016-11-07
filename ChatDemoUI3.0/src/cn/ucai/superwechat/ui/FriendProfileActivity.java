package cn.ucai.superwechat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hyphenate.easeui.domain.User;
import com.hyphenate.easeui.utils.EaseUserUtils;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.utils.MFGT;

/**
 * Created by Administrator on 2016/11/7.
 */
public class FriendProfileActivity extends BaseActivity {
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.iv_friend_profile_avatar)
    ImageView ivAvatar;
    @Bind(R.id.tv_friend_profile_nick)
    TextView tvNick;
    @Bind(R.id.tv_profile_username)
    TextView tvUsername;
    @Bind(R.id.tv_friend_profile_tag)
    TextView tvTag;
    @Bind(R.id.tv_friend_profile_more)
    TextView tvMore;
    @Bind(R.id.btn_add_friend)
    Button btnAddFriend;
    @Bind(R.id.btn_send_msg)
    Button btnSendMsg;
    @Bind(R.id.btn_send_video)
    Button btnSendVideo;
    @Bind(R.id.btn_send)
    Button btnSend;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.layout_friend_profile_view)
    RelativeLayout layoutFriendProfileView;
    User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);
        ButterKnife.bind(this);
        user = (User) getIntent().getSerializableExtra(I.User.USER_NAME);
        if (user == null) {
            MFGT.finish(this);
        }
        initView();
    }

    private void initView() {
        imgBack.setVisibility(View.VISIBLE);
        textTitle.setVisibility(View.VISIBLE);
        textTitle.setText(R.string.userinfo_txt_profile);
        setUserInfo();
        isFriend();
    }

    private void isFriend() {
        if (SuperWeChatHelper.getInstance().getAppContactList().containsKey(user.getMUserName())) {
            btnSendMsg.setVisibility(View.VISIBLE);
            btnSendVideo.setVisibility(View.VISIBLE);
        } else {
            btnAddFriend.setVisibility(View.VISIBLE);
        }
    }

    private void setUserInfo() {
        EaseUserUtils.setAppUserAvatar(this, user.getMUserName(), ivAvatar);
        EaseUserUtils.setAppUserNick(user.getMUserNick(), tvNick);
        EaseUserUtils.setAppUserNameWithNo(user.getMUserName(), tvUsername);
    }

    @OnClick({R.id.img_back, R.id.btn_add_friend, R.id.btn_send_msg})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MFGT.finish(this);
                break;
            case R.id.btn_add_friend:
                Intent intent = new Intent(this, AddFriendActicity.class);
                intent.putExtra(I.User.USER_NAME, user.getMUserName());
                MFGT.startActivity(this, intent);
                break;
            case R.id.btn_send_msg:
                break;
        }
    }
}
