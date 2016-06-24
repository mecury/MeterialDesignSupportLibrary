package mecury.meterialdesignsupportlibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 海飞 on 2016/6/23.
 */
public class CoordinationFragment extends Fragment {

    private View view;

    private FloatingActionButton fab_btn;
    private AppCompatButton button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fm_coordinator,container,false);
        fab_btn = (FloatingActionButton) view.findViewById(R.id.fab_btn);

        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(view,"你点击了snackbar",Snackbar.LENGTH_SHORT).show();
            }
        });

        button = (AppCompatButton) view.findViewById(R.id.act_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),CoordinationActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
