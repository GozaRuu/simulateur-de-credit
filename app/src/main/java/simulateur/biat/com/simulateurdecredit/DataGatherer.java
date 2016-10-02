package simulateur.biat.com.simulateurdecredit;

import android.os.Bundle;

import org.ksoap2.SoapFault;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class DataGatherer {

    public final ArrayList<ArrayList<ResponseType>> data = new ArrayList<ArrayList<ResponseType>>() ;
    private String methodName = null ;
    private String[] args = new String[10] ;

    DataGatherer(String methodName, String[] args){this.methodName = methodName ; this.args = args ;}

    public Boolean gather() throws InterruptedException {
        Thread T = new Thread(new Runnable() {
            @Override
            public void run() {
                CallSOAP SOAP = new CallSOAP() ;
                ArrayList<ResponseType> Obj = new ArrayList<>() ;
                switch (methodName) {
                    case "constantDuree" :
                        Obj = SOAP.RemboursementConstantDuree(args[0], args[1], args[2], args[3], args[4], args[5]);
                        data.add(Obj) ;
                        break ;
                    case "constantMontant" :
                        Obj = SOAP.RemboursementConstantMontant(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
                        data.add(Obj) ;
                        break ;
                    case "DegressifDuree" :
                        Obj = SOAP.RemboursementDegressifDuree(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
                        data.add(Obj) ;
                        break ;
                    case "DegressifMontant" :
                        Obj = SOAP.RemboursementDegressifMontant(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]);
                        data.add(Obj) ;
                        break ;
                    case "Debug" :
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        Obj.add(new ResponseType("12", "15", "450", "787", "87")) ;
                        data.add(Obj) ;
                        break ;
                }


            }
        });
        T.start();
        try {
            T.join();
        } catch (InterruptedException e) {
            return false ;
        }
        return true ;
    }
}
