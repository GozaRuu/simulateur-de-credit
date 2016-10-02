package simulateur.biat.com.simulateurdecredit;

import android.util.Log;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.PropertyInfo;
import org.xmlpull.v1.XmlPullParserException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Vector;

public class CallSOAP {

    private final String URL="http://172.28.70.210/SI_SERVICES/Credit.asmx";

    CallSOAP() {}

    private final SoapSerializationEnvelope getSoapSerializationEnvelope(SoapObject request) {
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false);
        envelope.setOutputSoapObject(request);
        return envelope;
    }

    private final HttpTransportSE getHttpTransportSE(String URL) {
        HttpTransportSE ht = new HttpTransportSE(URL);
        ht.debug = true;
        ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
        return ht;
    }

    public ArrayList<ResponseType> RemboursementConstantDuree(String Montant_de_la_tranche, String  Montant_Remboursement, String Taux, String Periodicite, String DateDeblocage, String PremiereEcheance)
    {
        final String NAMESPACE="http://BIATSI/SI_SERVICES/Credit";
        final String SOAP_ACTION = "http://BIATSI/SI_SERVICES/Credit/RemboursementConstantDuree";
        ArrayList<ResponseType> data = new ArrayList<ResponseType>() ;
        String methodname = "RemboursementConstantDuree";

        SoapObject request = new SoapObject(NAMESPACE, methodname);
        request.addProperty("Montant_de_la_tranche", Montant_de_la_tranche.toString());
        request.addProperty("Duree", Montant_Remboursement.toString());
        request.addProperty("Taux", Taux.toString());
        request.addProperty("Periodicite",Periodicite.toString());
        request.addProperty("DateDeblocage",DateDeblocage);
        request.addProperty("PremiereEcheance",PremiereEcheance);

        SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(request);
        HttpTransportSE ht = getHttpTransportSE(URL);
        try {
            ht.debug =true ;
            ht.call(SOAP_ACTION, envelope);
            SoapObject resultsString = (SoapObject) envelope.getResponse();
            if(resultsString!=null)
            {
                for (int i=0 ; i<resultsString.getPropertyCount() ;  i++)
                {
                    SoapObject so = (SoapObject)resultsString.getProperty(i) ;
                    ResponseType obj = new ResponseType();
                    obj.Echeance=so.getProperty("Echeance").toString();
                    obj.NombreDeJours=so.getProperty("NombreDeJours").toString();
                    obj.Encours=so.getProperty("Encours").toString();
                    obj.Amortissement=so.getProperty("Amortissement").toString();
                    obj.Interets=so.getProperty("Interets").toString();
                    data.add(obj) ;
                }
            }
            else
            {
                return null ;
            }

        }catch (SocketTimeoutException e) {
            e.printStackTrace();
        }catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public ArrayList<ResponseType> RemboursementConstantMontant(String Montant_de_la_tranche, String  Montant_Remboursement, String Taux, String Periodicite_Principal, String Periodicite_Interets, String DateDeblocage, String PremiereEcheancePrincipal, String PremiereEcheanceInterets)
    {
        final String NAMESPACE="http://BIATSI/SI_SERVICES/Credit";
        final String SOAP_ACTION = "http://BIATSI/SI_SERVICES/Credit/RemboursementConstantMontant";
        String response="";
        ArrayList<ResponseType> data = null ;
        String methodname = "RemboursementConstantMontant";

        SoapObject request = new SoapObject(NAMESPACE, methodname);
        request.addProperty("Montant_de_la_tranche", Double.parseDouble(Montant_de_la_tranche));
        request.addProperty("Montant_Remboursement", Integer.parseInt(Montant_Remboursement));
        request.addProperty("Taux", Double.parseDouble(Taux));
        request.addProperty("Periodicite_Principal",Integer.parseInt(Periodicite_Principal));
        request.addProperty("Periodicite_Interets",Integer.parseInt(Periodicite_Interets));
        request.addProperty("DateDeblocage",DateDeblocage);
        request.addProperty("PremiereEcheancePrincipal",PremiereEcheancePrincipal);
        request.addProperty("PremiereEcheanceInterets",PremiereEcheanceInterets);

        SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(request);
        HttpTransportSE ht = getHttpTransportSE(URL);
        try {
            ht.debug =true ;
            ht.call(SOAP_ACTION, envelope);
            SoapObject resultsString = (SoapObject) envelope.getResponse();
            if(resultsString!=null)
            {
                for (int i=0 ; i<resultsString.getPropertyCount() ;  i++)
                {
                    SoapObject so = (SoapObject)resultsString.getProperty(i) ;
                    ResponseType obj = new ResponseType();
                    obj.Echeance=so.getProperty("Echeance").toString();
                    obj.NombreDeJours=so.getProperty("NombreDeJours").toString();
                    obj.Encours=so.getProperty("Encours").toString();
                    obj.Amortissement=so.getProperty("Amortissement").toString();
                    obj.Interets=so.getProperty("Interets").toString();
                    data.add(obj) ;
                }
            }
            else
            {
                return null ;
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public ArrayList<ResponseType> RemboursementDegressifDuree(String Montant_de_la_tranche, String  Duree_Tranche, String Taux, String Periodicite_Principal, String Periodicite_Interets, String DateDeblocage, String PremiereEcheancePrincipal, String PremiereEcheanceInterets)
    {
        final String NAMESPACE="http://BIATSI/SI_SERVICES/Credit";
        final String SOAP_ACTION = "http://BIATSI/SI_SERVICES/Credit/RemboursementDegressifDuree";
        String response="";
        ArrayList<ResponseType> data = null ;
        String methodname = "RemboursementDegressifDuree";

        SoapObject request = new SoapObject(NAMESPACE, methodname);
        request.addProperty("Montant_de_la_tranche", Double.parseDouble(Montant_de_la_tranche));
        request.addProperty("Duree_Tranche", Integer.parseInt(Duree_Tranche));
        request.addProperty("Taux", Double.parseDouble(Taux));
        request.addProperty("Periodicite_Principal",Integer.parseInt(Periodicite_Principal));
        request.addProperty("Periodicite_Interets",Integer.parseInt(Periodicite_Interets));
        request.addProperty("DateDeblocage",DateDeblocage);
        request.addProperty("PremiereEcheancePrincipal",PremiereEcheancePrincipal);
        request.addProperty("PremiereEcheanceInterets",PremiereEcheanceInterets);

        SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(request);
        HttpTransportSE ht = getHttpTransportSE(URL);
        try {
            ht.debug =true ;
            ht.call(SOAP_ACTION, envelope);
            SoapObject resultsString = (SoapObject) envelope.getResponse();
            if(resultsString!=null)
            {
                for (int i=0 ; i<resultsString.getPropertyCount() ;  i++)
                {
                    SoapObject so = (SoapObject)resultsString.getProperty(i) ;
                    ResponseType obj = new ResponseType();
                    obj.Echeance=so.getProperty("Echeance").toString();
                    obj.NombreDeJours=so.getProperty("NombreDeJours").toString();
                    obj.Encours=so.getProperty("Encours").toString();
                    obj.Amortissement=so.getProperty("Amortissement").toString();
                    obj.Interets=so.getProperty("Interets").toString();
                    data.add(obj) ;
                }
            }
            else
            {
                return null ;
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public ArrayList<ResponseType> RemboursementDegressifMontant(String Montant_de_la_tranche, String  Montant_Remboursement, String Taux, String Periodicite_Principal, String Periodicite_Interets, String DateDeblocage, String PremiereEcheancePrincipal, String PremiereEcheanceInterets)
    {
        final String NAMESPACE="http://BIATSI/SI_SERVICES/Credit";
        final String SOAP_ACTION = "http://BIATSI/SI_SERVICES/Credit/RemboursementDegressifMontant";
        String response="";
        ArrayList<ResponseType> data = null ;
        String methodname = "RemboursementDegressifMontant";

        SoapObject request = new SoapObject(NAMESPACE, methodname);
        request.addProperty("Montant_de_la_tranche", Double.parseDouble(Montant_de_la_tranche));
        request.addProperty("Montant_Remboursement", Integer.parseInt(Montant_Remboursement));
        request.addProperty("Taux", Double.parseDouble(Taux));
        request.addProperty("Periodicite_Principal",Integer.parseInt(Periodicite_Principal));
        request.addProperty("Periodicite_Interets",Integer.parseInt(Periodicite_Interets));
        request.addProperty("DateDeblocage",DateDeblocage);
        request.addProperty("PremiereEcheancePrincipal",PremiereEcheancePrincipal);
        request.addProperty("PremiereEcheanceInterets",PremiereEcheanceInterets);

        SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(request);
        HttpTransportSE ht = getHttpTransportSE(URL);
        try {
            ht.debug =true ;
            ht.call(SOAP_ACTION, envelope);
            SoapObject resultsString = (SoapObject) envelope.getResponse();
            if(resultsString!=null)
            {
                for (int i=0 ; i<resultsString.getPropertyCount() ;  i++)
                {
                    SoapObject so = (SoapObject)resultsString.getProperty(i) ;
                    ResponseType obj = new ResponseType();
                    obj.Echeance=so.getProperty("Echeance").toString();
                    obj.NombreDeJours=so.getProperty("NombreDeJours").toString();
                    obj.Encours=so.getProperty("Encours").toString();
                    obj.Amortissement=so.getProperty("Amortissement").toString();
                    obj.Interets=so.getProperty("Interets").toString();
                    data.add(obj) ;
                }
            }
            else
            {
                return null ;
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (SoapFault soapFault) {
            soapFault.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

}
