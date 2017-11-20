package com.example.usuario.dynamicfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Clase Fragment que maneja un texto que ocupa toda la pantalla
 * y debe restablecerse al girarla
 *
 * @author  Enrique Casielles Lapeira
 * @version 1.0
 * @see android.app.Fragment
 */
public class FragmentB extends Fragment {

    public static final String FRAG_B = "FragmentB";
    private TextView txvMessage;
    private String message;

    public FragmentB() { }

    public void changeTextAndSize(String message, int size){
        txvMessage.setTextSize(size);
        txvMessage.setText(message);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentb, container, false);
        txvMessage = rootView.findViewById(R.id.txvTexto);
        return rootView;
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message", txvMessage.getText().toString());
        outState.putFloat("size", txvMessage.getTextSize());
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            txvMessage.setText(savedInstanceState.getString("message"));
            txvMessage.setTextSize(savedInstanceState.getFloat("size"));
        }
    }
    /**
     * Inicializa la vista con los Fragment
     * @param view Vista sobre la que se infla el Fragment
     * @param savedInstanceState Estado de la Activity
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d("FragmentB", "onViewCreated()");
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null)
            changeTextAndSize(bundle.getString("message"), bundle.getInt("size"));
    }

}
