package cn.ucai.superwechat.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.easeui.utils.EaseUserUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechat.I;
import cn.ucai.superwechat.R;
import cn.ucai.superwechat.utils.MFGT;

/**
 * Created by Administrator on 2016/11/7.
 */
public class AddFriendActicity extends BaseActivity {
    @Bind(R.id.img_back)
    ImageView imgBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.btn_send)
    Button btnSend;
    @Bind(R.id.edit_note)
    EditText editNote;
    private ProgressDialog progressDialog;
    String userName = null;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ctivity_add_friend);
        ButterKnife.bind(this);
        userName = getIntent().getStringExtra(I.User.USER_NAME);
        if (userName == null) {
            MFGT.finish(this);
        }
        initView();
    }

    private void initView() {
        imgBack.setVisibility(View.VISIBLE);
        textTitle.setVisibility(View.VISIBLE);
        textTitle.setText(getString(R.string.add_friend));
        btnSend.setVisibility(View.VISIBLE);
        msg = getString(R.string.addcontact_send_msg_prefix) + EaseUserUtils.getCurrentAppUserInfo().getMUserNick();
        editNote.setText(msg);
        editNote.setSelection(2,editNote.getText().toString().length());
    }

    @OnClick({R.id.img_back, R.id.btn_send})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                MFGT.finish(this);
                break;
            case R.id.btn_send:
                sendMessage();
                break;
        }
    }

    private void sendMessage() {
        progressDialog = new ProgressDialog(this);
        String stri = getResources().getString(R.string.addcontact_adding);
        progressDialog.setMessage(stri);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        new Thread(new Runnable() {
            public void run() {
                try {
                    //demo use a hardcode reason here, you need let user to input if you like
                    String s = getResources().getString(R.string.Add_a_friend);
                    msg = editNote.getText().toString().trim();
                    EMClient.getInstance().contactManager().addContact(userName, msg);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s1 = getResources().getString(R.string.send_successful);
                            Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();
                            MFGT.finish(AddFriendActicity.this);
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s2 = getResources().getString(R.string.Request_add_buddy_failure);
                            Toast.makeText(getApplicationContext(), s2 + e.getMessage(), Toast.LENGTH_LONG).show();
                            MFGT.finish(AddFriendActicity.this);
                        }
                    });
                }
            }
        }).start();
    }
}
