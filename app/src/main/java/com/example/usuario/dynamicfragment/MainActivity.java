package com.example.usuario.dynamicfragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity implements FragmentA.FragmentAListener{
    //Vamos a usar la librería app antigua
    private Fragment fragmentA;
    private Fragment fragmentB;
    public static final String ACTIVITY = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FragmentManager añade fragmentos a una actividad
        FragmentManager fragmentManager = getFragmentManager();

        fragmentA = fragmentManager.findFragmentByTag(FragmentA.FRAG_A);
        if(fragmentA == null) {
            fragmentA = new FragmentA();
            //FragmentTransaction gestiona las transacciones de fragment
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //Add crea y añade un fragment a la transacción
            fragmentTransaction.add(android.R.id.content, fragmentA);
            //Confirma la transacción
            fragmentTransaction.commit();
        }
        Log.d(ACTIVITY, "onCreate()");
    }
    @Override
    public void onFragmentAEvent(String mensaje, int size) {
        fragmentB = new FragmentB();
        Bundle bundle = new Bundle();
        bundle.putString("message", mensaje);
        bundle.putInt("size", size);

        //Con el método setArguments se pasan argumentos o información a un Fragment
        //Deben agregarse antes de añadir el Fragment a la transacción
        //fragmentB.setArguments(bundle); <- Lo quitamos para usar patrón Factory

        //Se debe utilizar el patrón Factory, donde la creación del objeto
        //y el paso de argumentos se ejecutan consecutivamente
        fragmentB = FragmentB.newInstance(bundle);

        //Comienza la transacción de FragmentA a FragmentB
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //Ponemos content porque realmente sólo trabajaremos con una activity y muchos Fragment
        fragmentTransaction.replace(android.R.id.content, fragmentB);

        //Antes de realizar el commit se debe guardar la transacción.
        //Se puede guardar un cambio en un string
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        Log.d(ACTIVITY, "onFragmentAEvent()");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(ACTIVITY, "onStart()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(ACTIVITY, "onPause()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(ACTIVITY, "onStop()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(ACTIVITY, "onDestroy()");
    }

}
