package com.hack.fragmentsinteractiondemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    loginFragmentListener mListener;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(getActivity() instanceof loginFragmentListener){
            mListener = (loginFragmentListener) getActivity();
        } else {
            throw  new RuntimeException("Host Acivity must implements loginFragmentListenr");
        }
    }


    public interface loginFragmentListener{
        void onUserLogin(String user);
        void onUserLoginDenied();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_login, container, false);
        final EditText ed_login = v.findViewById(R.id.ed_login);
        final EditText ed_psw = v.findViewById(R.id.ed_password);

        Button btn_login = v.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = ed_login.getText().toString();
                String psw = ed_psw.getText().toString();
                if(MainActivity.login1.equals(login)&& MainActivity.psw1.equals(psw)){
                   mListener.onUserLogin(login);
                } else {
                  mListener.onUserLoginDenied();
                }
            }
        });
        return v;
    }
}
