package com.bakalris.example.basicandroidsample;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author lukassos@gmail.com
 * @date 4/6/2016
 * @time 10:58 AM
 * All rights reserved
 */

public class Kofola {
    public static final String NAZOV = "nazov";
    public static final String OBJEM = "objem";

    private String nazov;
    private int objem;
    private static Kofola mInstance;

    public Kofola() {
        if(mInstance == null){
            mInstance = this;
        }

    }

    public Kofola(
            String nazov,
            int objem
            ) {
        this.nazov = nazov;
        this.objem = objem;
    }

    public Kofola(String jsonStr) {
        try {
            JSONObject json = new JSONObject(jsonStr);
            nazov = json.optString(NAZOV);
            objem = json.optInt(OBJEM);
        } catch (JSONException e) {
        }
    }

    @Override
    public String toString() {
        try {
            JSONObject json = new JSONObject();

            json.put(NAZOV, nazov);
            json.put(OBJEM, objem);

            return json.toString();
        } catch (JSONException e) {
        }
        return "{}";
    }

    public String getNazov() {
        return nazov;
    }

    public void setNazov(String nazov) {
        this.nazov = nazov;
    }

    public int getObjem() {
        return objem;
    }

    public void setObjem(int objem) {
        this.objem = objem;
    }

}
