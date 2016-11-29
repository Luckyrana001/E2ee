package com.example.ranalucky.e2ee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.isprint.e2eea.client.AxMxE2EEClient;

import static android.R.attr.publicKey;
import static com.isprint.e2eea.client.AxMxE2EEClient.ALGO_ID_3DES_SHA1;

public class MainActivity extends AppCompatActivity {
    String e2eesid;String pin;String publicKey;String serverRandom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      String RPIN =  E2EEkeyGeneration( e2eesid, pin, publicKey, serverRandom);


}

    private String E2EEkeyGeneration(String e2eesid,String pin,String publicKey,String serverRandom) {
        String RPIN = "";
        AxMxE2EEClient e2ee = new AxMxE2EEClient();
        try {
            RPIN = e2ee.encryptForLogin  (
                        /** Default value*/
                        ALGO_ID_3DES_SHA1, e2eesid, pin, publicKey, serverRandom);
            int retCode = e2ee.getRetCode();
            if(retCode ==0)
            {
                //successful
                Toast.makeText(getApplicationContext(),RPIN,Toast.LENGTH_LONG).show();

            } else {
                if(e2ee.isInvalidPin(retCode)){
                    //invalid pin
                    Toast.makeText(getApplicationContext(),"invalid pin",Toast.LENGTH_LONG).show();

                } else {
                    if (e2ee.isError(retCode)) {
                        // is error
                        Toast.makeText(getApplicationContext(),"There is a error.",Toast.LENGTH_LONG).show();

                    }
                }}}
                    catch(Exception e){
                        Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
                }


        return  RPIN;
    }
    }
