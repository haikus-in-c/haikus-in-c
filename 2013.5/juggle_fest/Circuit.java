/*  Circuit class for Yodle JuggleFest Programming Challenge
    by Ian Zapolsky */

import java.util.StringTokenizer;
import java.util.ArrayList;

public class Circuit {

    String name;
    ArrayList<Juggler> jugglers;
    ArrayList<Juggler> overflow;
    int target, H, E, P;

    public Circuit(String identity, int init_jugglers, int init_target) {
        setIdentity(identity);
        target = init_target;
        jugglers = new ArrayList<Juggler>();
        overflow = new ArrayList<Juggler>();
    }

    private void setIdentity(String identity) {
        StringTokenizer t = new StringTokenizer(identity);
        String h, e, p;
        h = t.nextToken();
        name = t.nextToken();
        h = t.nextToken();
        e = t.nextToken();
        p = t.nextToken();
        String[] tmp = new String[2];
        tmp = h.split(":");
        H = Integer.valueOf(tmp[1]);
        tmp = e.split(":");
        E = Integer.valueOf(tmp[1]);
        tmp = p.split(":");
        P = Integer.valueOf(tmp[1]);
    }

    
    public void addJuggler(Juggler j) {
        int dotProd = j.getDotProduct(this);
        int i = 0;  
        boolean placed = false;
        if (jugglers.size() == 0)
            jugglers.add(j);
        else {
            while (i < jugglers.size()) {
                if (dotProd > jugglers.get(i).getDotProduct(this)) {
                    jugglers.add(jugglers.get(jugglers.size()-1));
                    for (int k = jugglers.size()-2; k >= i; k--)
                        jugglers.set((k+1), jugglers.get(k));
                    jugglers.set(i, j);
                    placed = true;
                    if (jugglers.size() > target) {
                        Juggler extra = jugglers.get(jugglers.size()-1);
                        jugglers.remove(jugglers.size()-1);
                        overflow.add(extra);
                    }
                    break;
                }
                else
                    i++;
            }
            if (i < target && placed == false) 
                jugglers.add(j);
            else if (placed == false)
                overflow.add(j);
        }   
    }

    public Juggler getLowestPerformer() {
        Juggler worst = overflow.get(overflow.size()-1);
        overflow.remove(overflow.size()-1);
        return worst;
    }

    public boolean checkJuggler(Juggler j) {
        int dotProd = j.getDotProduct(this);
        for (int i = 0; i < jugglers.size(); i++) {
            if (dotProd > jugglers.get(i).getDotProduct(this))
                return true;
        }
        return false;
    }
    
    public boolean isUnder() {
        if (jugglers.size() < target && overflow.size() == 0)
            return true;
        else
            return false;
    }

    public boolean isFull() {
        if (jugglers.size() == target && overflow.size() == 0)
            return true;
        else
            return false;
    }

    public boolean isOver() {
        if (jugglers.size() == target && overflow.size() > 0)
            return true;
        else
            return false;
    }

    public String getName() { return name; }

    public int getH() { return H; }
    
    public int getE() { return E; }
    
    public int getP() { return P; }

    public String toString() { return name; }
}
