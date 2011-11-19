/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import com.googlecode.objectify.Key;
import data.Team;

/**
 *
 * @author ondrej
 */

public class TableRow {
    private Key<Team> team;
    private int w = 0;
    private int d = 0;
    private int l = 0;
    private int gs = 0;
    private int go = 0;

    public TableRow(Key<Team> team) {
        this.team = team;
    }

    public int getD() {
        return d;
    }

    public int getGo() {
        return go;
    }

    public int getGs() {
        return gs;
    }

    public int getL() {
        return l;
    }

    public Key<Team> getTeam() {
        return team;
    }

    public int getW() {
        return w;
    }

    public int getP() {
        return 3 * w + d;
    }

    public int getM() {
        return w + d + l;
    }

    public void addD() {
        this.d += 1;
    }

    public void addGo(int go) {
        this.go += go;
    }

    public void addGs(int gs) {
        this.gs += gs;
    }

    public void addL() {
        this.l += 1;
    }

    public void addW() {
        this.w += 1;
    }


}
