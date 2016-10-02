package simulateur.biat.com.simulateurdecredit;

public class ResponseType {
    String Echeance;
    String NombreDeJours;
    String Encours;
    String Amortissement;
    String Interets;

    ResponseType(){}

    ResponseType(String Echeance, String NombreDeJours, String Encours, String Amortissement, String Interets)
    {
        this.Echeance=Echeance ;
        this.NombreDeJours=NombreDeJours ;
        this.Encours=Encours ;
        this.Amortissement = Amortissement ;
        this.Interets = Interets ;
    }
}
