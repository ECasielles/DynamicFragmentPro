package com.example.usuario.dynamicfragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

/**
 * Clase Fragment que implementa cuadro de texto,
 * barra de desplazamiento y botón para girar la pantalla y adaptarla
 *
 * @author Enrique Casielles Lapeira
 * @version 1.0
 * @see android.app.Fragment
 */
public class FragmentA extends Fragment {
    //Interfaz que va a corresponder a la Activity y los métodos que ésta hereda
    public static final String FRAG_A = "FragmentA";
    private FragmentAListener mCallBack;
    private EditText edtMessage;
    private Button btnSize;
    private SeekBar skbSize;

    /**
     * Se define la inerfaz que servirá de contrato entre el Fragment y la Activity
     **/
    public interface FragmentAListener {
        void onFragmentAEvent(String mensaje, int size);
    }
    public FragmentA() { }
    /**
     * Obsoleto desde API 23
     * @param activity Actividad padre del fragment
     */
    @Override
    public void onAttach(Activity activity) {
        Log.d("FragmentA", "onAttach(activity)");
        super.onAttach(activity);
        try {
            mCallBack = (FragmentAListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement FragmentAListener");
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("FragmentA", "onCreateView()");
        //Por corrección implementa el superconstructor aunque no lo ponga el IDE.
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragmenta, container, false);
        if (rootView != null) {
            edtMessage = (EditText) rootView.findViewById(R.id.edtMessage);
            skbSize = (SeekBar) rootView.findViewById(R.id.skbSize);
            btnSize = (Button) rootView.findViewById(R.id.btnSize);
            btnSize.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mCallBack.onFragmentAEvent(edtMessage.getText().toString(), skbSize.getProgress());
                }
            });
        }
        return rootView;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d("FragmentA", "onViewCreated()");
        super.onViewCreated(view, savedInstanceState);
        btnSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCallBack.onFragmentAEvent(edtMessage.getText().toString(), skbSize.getProgress());
            }
        });
    }
    /**
     * Este método solo funciona desde la API 23 en adelante.
     * Si se ejecuta en una API inferior NO DA ERROR PERO NO FUNCIONA LA COMUNICACIÓN Activity-Fragment
      * */
    @Override
    public void onAttach(Context context) {
        Log.d("FragmentA", "onAttach(context)");
        super.onAttach(context);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("FragmentA", "onActivityCreated()");
        super.onActivityCreated(savedInstanceState);
    }

}
