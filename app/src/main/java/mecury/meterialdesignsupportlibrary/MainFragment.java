package mecury.meterialdesignsupportlibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by 海飞 on 2016/6/23.
 */
public class MainFragment extends Fragment {


    private View view;

    public MainFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_main, container, false);
        final TextInputLayout inputLayout = (TextInputLayout) view.findViewById(R.id.textInput);
        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingButton);

        inputLayout.setHint("请输入：");
        final EditText editText = inputLayout.getEditText();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("TAG",s.length()+"");
                if (s.length() > 4){//字符超过5个时，出现EditText提示
                    inputLayout.setError("字符不能超过5个");
                    inputLayout.setErrorEnabled(true);
                }else{
                    inputLayout.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Snackbar snackbar = Snackbar.make(view,"你点击按钮",Snackbar.LENGTH_SHORT);
                snackbar.show();
                snackbar.setAction("知道了", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
            }
        });

        return view;
    }
}
