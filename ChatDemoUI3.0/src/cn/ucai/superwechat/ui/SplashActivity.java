package cn.ucai.superwechat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.domain.User;

import java.util.Map;

import cn.ucai.superwechat.SuperWeChatHelper;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.bean.Result;
import cn.ucai.superwechat.data.NetDao;
import cn.ucai.superwechat.data.OkHttpUtils;
import cn.ucai.superwechat.db.UserDao;
import cn.ucai.superwechat.utils.L;
import cn.ucai.superwechat.utils.ResultUtils;

/**
 * 开屏页
 */
public class SplashActivity extends BaseActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();
    private static final int sleepTime = 2000;
    SplashActivity mContext;

    @Override
    protected void onCreate(Bundle arg0) {
        setContentView(R.layout.em_activity_splash);
        super.onCreate(arg0);

        RelativeLayout rootLayout = (RelativeLayout) findViewById(R.id.splash_root);
        mContext = this;
        // TextView versionText = (TextView) findViewById(R.id.tv_version);

        //versionText.setText(getVersion());
        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
        animation.setDuration(1500);
        rootLayout.startAnimation(animation);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Thread(new Runnable() {
            public void run() {
                if (SuperWeChatHelper.getInstance().isLoggedIn()) {
                    // auto login mode, make sure all group and conversation is loaed before enter the main screen
                    long start = System.currentTimeMillis();
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    UserDao dao = new UserDao(mContext);
                    User user = dao.getUser(EMClient.getInstance().getCurrentUser());
                    if (user != null) {
                        L.e(TAG, user.toString());
                        SuperWeChatHelper.getInstance().setCurrentUser(user);

                    }
                    long costTime = System.currentTimeMillis() - start;
                    //wait
                    if (sleepTime - costTime > 0) {
                        try {
                            Thread.sleep(sleepTime - costTime);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //enter main screen
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {
                    try {
                        Thread.sleep(sleepTime);
                    } catch (InterruptedException e) {
                    }
                    startActivity(new Intent(SplashActivity.this, WelcomeActivity.class));
                    finish();
                }
            }
        }).start();

    }

    /**
     * get sdk version
     */
    private String getVersion() {
        return EMClient.getInstance().getChatConfig().getVersion();
    }
}
