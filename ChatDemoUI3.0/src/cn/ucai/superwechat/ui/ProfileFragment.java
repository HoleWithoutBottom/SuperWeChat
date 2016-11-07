package cn.ucai.superwechat.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easemob.redpacketui.utils.RedPacketUtil;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.Constant;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    @Bind(R.id.iv_profile_avatar)
    ImageView ivProfileAvatar;
    @Bind(R.id.tv_profile_nick)
    TextView tvProfileNick;
    @Bind(R.id.tv_profile_username)
    TextView tvProfileUsername;
    @Bind(R.id.layout_profile_view)
    RelativeLayout layoutProfileView;
    @Bind(R.id.tv_profile_album)
    TextView tvProfileAlbum;
    @Bind(R.id.tv_profile_collect)
    TextView tvProfileCollect;
    @Bind(R.id.tv_profile_money)
    TextView tvProfileMoney;
    @Bind(R.id.tv_profile_smail)
    TextView tvProfileSmail;
    @Bind(R.id.tv_profile_setting)
    TextView tvProfileSetting;

    public ProfileFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 没有登陆
        if (savedInstanceState != null && savedInstanceState.getBoolean("isConflict", false))
            return;
        setUserInfo();
    }

    private void setUserInfo() {
        EaseUserUtils.setCurrentAppUserAvatar(getActivity(), ivProfileAvatar);
        EaseUserUtils.setCurrentAppUserNick(tvProfileNick);
        EaseUserUtils.setCurrentAppUserNameWithNo(tvProfileUsername);
    }

    @OnClick({R.id.layout_profile_view, R.id.tv_profile_album, R.id.tv_profile_collect, R.id.tv_profile_money, R.id.tv_profile_smail, R.id.tv_profile_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_profile_view:
                MFGT.startActivity(getActivity(), new Intent(getActivity(), UserProfileActivity.class));
                break;
            case R.id.tv_profile_album:
                break;
            case R.id.tv_profile_collect:
                break;
            case R.id.tv_profile_money:
                // 进入零钱页面
                RedPacketUtil.startChangeActivity(getActivity());
                break;
            case R.id.tv_profile_smail:
                break;
            case R.id.tv_profile_setting:
                MFGT.startActivity(getActivity(), new Intent(getActivity(), SettingsActivity.class));
                break;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (((MainActivity) getActivity()).isConflict) {
            outState.putBoolean("isConflict", true);
        } else if (((MainActivity) getActivity()).getCurrentAccountRemoved()) {
            outState.putBoolean(Constant.ACCOUNT_REMOVED, true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        setUserInfo();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
