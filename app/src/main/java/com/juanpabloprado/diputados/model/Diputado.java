package com.juanpabloprado.diputados.model;

import android.net.Uri;

import com.juanpabloprado.diputados.utils.ParseConstants;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

/**
 * Created by Juan on 9/13/2015.
 */
@ParseClassName(ParseConstants.CLASS_DIPUTADOS)
public class Diputado extends ParseObject {
    public String getNumber() {
        return getString(ParseConstants.KEY_NUMBER);
    }

    public String getName() {
        return getString(ParseConstants.KEY_NAME);
    }

    public String getParty() {
        return getString(ParseConstants.KEY_PARTY);
    }

    public String getCabezera() {
        return getString(ParseConstants.KEY_CABEZERA);
    }

    public String getCircunscripcion() {
        return getString(ParseConstants.KEY_CIRCUNSCRIPCION);
    }
    public String getCorreo() {
        return getString(ParseConstants.KEY_CORREO);
    }
    public String getCurul() {
        return getString(ParseConstants.KEY_CURUL);
    }
    public String getEntidad() {
        return getString(ParseConstants.KEY_ENTIDAD);
    }
    public String getTipoEleccion() {
        return getString(ParseConstants.KEY_TIPO_ELECCION);
    }
    public List<ParseObject> getAcademicos() {
        // Object Array
        return getList(ParseConstants.KEY_ACADEMICOS);
    }
    public List<Object> getAsistencias() {
        // Object Array
        return getList(ParseConstants.KEY_ASISTENCIAS);
    }

    public static void findAllInBackground(final FindCallback<Diputado> callback) {
        ParseQuery<Diputado> diputadoQuery = ParseQuery.getQuery(Diputado.class);
        diputadoQuery.findInBackground(new FindCallback<Diputado>() {

            @Override
            public void done(List<Diputado> diputados, ParseException e) {
                if (e == null) {
                    callback.done(diputados, null);
                } else {
                    callback.done(null, e);
                }
            }
        });
    }

    public static String getDiputadoId(Uri uri) {
        List<String> path = uri.getPathSegments();
        if (path.size() != 2 || !"diputado".equals(path.get(0))) {
            throw new RuntimeException("Invalid URI for talk: " + uri);
        }
        return path.get(1);
    }
}
