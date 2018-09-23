package marcosylvain.nesztler.fr.marcosylvain;

import android.os.Parcel;
import android.os.Parcelable;

public class Base implements Parcelable {

    private String codeTLCD;
    private String codeBDDP;
    private String libelle;
    private String amdtIrdd;

    public Base(String codeTLCD, String codeBDDP, String libelle, String amdtIrdd) {
        this.codeTLCD=codeTLCD;
        this.codeBDDP=codeBDDP;
        this.libelle=libelle;
        this.amdtIrdd=amdtIrdd;
    }

    protected Base(Parcel in) {
        codeTLCD = in.readString();
        codeBDDP = in.readString();
        libelle = in.readString();
        amdtIrdd = in.readString();
    }

    public static final Creator<Base> CREATOR = new Creator<Base>() {
        @Override
        public Base createFromParcel(Parcel in) {
            return new Base(in);
        }

        @Override
        public Base[] newArray(int size) {
            return new Base[size];
        }
    };

    public String getCodeTLCD() {
        return codeTLCD;
    }

    public String getCodeBDDP() {
        return codeBDDP;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getAmdtIrdd() {
        return amdtIrdd;
    }

    public String toString() {
        String Newligne = System.getProperty("line.separator");
        return ( Newligne + "Code   : " + getCodeTLCD() + Newligne + "Libellé : " + getLibelle() + Newligne + "             : " + getAmdtIrdd());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(codeTLCD);
        parcel.writeString(codeBDDP);
        parcel.writeString(libelle);
        parcel.writeString(amdtIrdd);
    }
}
