/*  Juggler class for Yodle JuggleFest Programming Challenge
    by Ian Zapolsky */

import java.util.StringTokenizer;

public class Juggler {

    String name;
    String[] preferences;
    int noPrefs;
    int preference, H, E, P;
    boolean last;

    public Juggler(String identity) {
        setIdentity(identity);
        preference = 0;
        last = false;
    }

    private void setIdentity(String identity) {
        StringTokenizer token = new StringTokenizer(identity);
        String h, e, p, prefs;
        h = token.nextToken();
        name = token.nextToken();
        h = token.nextToken();
        e = token.nextToken();
        p = token.nextToken();
        prefs = token.nextToken();
        String[] tmp = new String[3]; 
        tmp = h.split(":");
        H = Integer.valueOf(tmp[1]);
        tmp = e.split(":");
        E = Integer.valueOf(tmp[1]);
        tmp = p.split(":");
        P = Integer.valueOf(tmp[1]);
        preferences = prefs.split(",");
        noPrefs = preferences.length;
    } 

    public String getPref() { 
        if (preference == preferences.length) {
            last = true;
            return preferences[preference-1];
        }
        else
            return preferences[preference++];
    }

    public int getDotProduct(Circuit c) { return ((H*c.getH())+(E*c.getE())+(P*c.getP())); }

    public boolean isLast() { return last; }

    public String toString() { return name; }

}
