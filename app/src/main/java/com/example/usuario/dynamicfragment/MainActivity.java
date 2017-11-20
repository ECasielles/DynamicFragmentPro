package com.example.usuario.dynamicfragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity implements FragmentA.FragmentAListener{
    //Vamos a usar la librería app antigua
    private Fragment fragmentA;
    private Fragment fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //FragmentManager añade fragmentos a una actividad
        FragmentManager fragmentManager = getFragmentManager();

        //fragmentA = fragmentManager.getFragment(bundle, "fragmentA");
        //fragmentA = fragmentManager.findFragmentById("R.id.fragmentA");
        fragmentA = fragmentManager.findFragmentByTag(FragmentA.FRAG_A);
        if(fragmentA == null) {
            fragmentA = new FragmentA();
            //FragmentTransaction gestiona las transacciones de fragment
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //Añade un fragment a la transacción
            fragmentTransaction.add(android.R.id.content, fragmentA);
            //Confirma la transacción
            fragmentTransaction.commit();
        }

    }
    @Override
    public void onFragmentAEvent(String mensaje, int size) {
        fragmentB = new FragmentB();
        Bundle bundle = new Bundle();
        bundle.putString("message", mensaje);
        bundle.putInt("size", size);

        //Con el método setArguments se pasan argumentos o información a un Fragment
        //Deben agregarse antes de añadir el Fragment a la transacción
        fragmentB.setArguments(bundle);

        //Comienza la transacción de FragmentA a FragmentB
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        //Ponemos content porque realmente sólo trabajaremos con una activity y muchos Fragment
        fragmentTransaction.replace(android.R.id.content, fragmentB);
        fragmentTransaction.commit();
    }

}
