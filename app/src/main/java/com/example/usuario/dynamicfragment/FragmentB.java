package com.example.usuario.dynamicfragment;

import android.app.Activity;
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
    //Lo habitual será tener aquí un ArrayList de datos
    private TextView txvMessage;
    private String message;
    private int size;

    public static final String FRAG_B = "FragmentB";

    /**
     * Patrón Factory, que es una simplificación del patrón Builder.
     * @param bundle
     * @return
     */
    public static Fragment newInstance(Bundle bundle) {
        FragmentB fragmentB = new FragmentB();
        if(bundle != null) {
            fragmentB.setArguments(bundle);
        }
        return fragmentB;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d(FRAG_B, "onAttach()");
    }

    /**
     * ATENCION: Para que el estado dinámico de un Fragmnet sea permanente
     * ante un cambio de configuración usar setRetainInstance().
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //USAR CON PRUDENCIA: Guarda todos los datos SIEMPRE y evita
        //savedInstanceState y onViewStateRestored
        setRetainInstance(true);
        Log.d(FRAG_B, "onCreate()");
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentb, container, false);
        txvMessage = rootView.findViewById(R.id.txvTexto);
        Log.d(FRAG_B, "onCreateView()");
        return rootView;
    }
    /**
     * Inicializa la vista con los Fragment
     * @param view Vista sobre la que se infla el Fragment
     * @param savedInstanceState Estado de la Activity
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        //No hay cambio de configuración. Se ejecuta por primera vez
        if (savedInstanceState == null) {
            //Si hay parámetros, se asignan
            if (bundle != null) {
                message = bundle.getString("message");
                size = bundle.getInt("size");
            }
        }
        changeTextAndSize(message, size);
        Log.d(FRAG_B, "onViewCreated()");
    }
    public void changeTextAndSize(String message, int size){
        txvMessage.setText(message);
        txvMessage.setTextSize(size);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null){
            txvMessage.setText(savedInstanceState.getString("message"));
            txvMessage.setTextSize(savedInstanceState.getFloat("size"));
        }
        Log.d(FRAG_B, "onActivityCreated()");
    }
    //Comentamos para poder meterle el patrón Factory
    /*
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("message", txvMessage.getText().toString());
        outState.putFloat("size", txvMessage.getTextSize());
    }
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null) {
            txvMessage.setText(savedInstanceState.getString("message"));
            txvMessage.setTextSize(savedInstanceState.getFloat("size"));
        }
        Log.d(FRAG_B, "onViewStateRestored()");
    }
    */
    @Override
    public void onStart() {
        super.onStart();
        Log.d(FRAG_B, "onStart()");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(FRAG_B, "onPause()");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(FRAG_B, "onStop()");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(FRAG_B, "onDestroy()");
    }
    @Override
    public void onDestroyView() {
        Log.d(FRAG_B, "onDestroyView()");
        super.onDestroyView();
    }
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(FRAG_B, "onDetach()");
    }
}
