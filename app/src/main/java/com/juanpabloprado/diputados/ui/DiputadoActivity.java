package com.juanpabloprado.diputados.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.juanpabloprado.diputados.R;
import com.juanpabloprado.diputados.model.Diputado;
import com.juanpabloprado.diputados.utils.ParseConstants;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

public class DiputadoActivity extends AppCompatActivity {

    private static final String TAG = DiputadoActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diputado);

        // Fetch the data about this talk from Parse.
        String diputadoId = Diputado.getDiputadoId(getIntent().getData());

        ParseQuery<ParseObject> query = ParseQuery.getQuery(ParseConstants.CLASS_DIPUTADOS);
        query.getInBackground(diputadoId, new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject diputado, ParseException e) {
                updateView(diputado);
            }
        });

    }

    private void updateView(final ParseObject diputado) {
        TextView nameView = (TextView) findViewById(R.id.name);
        ImageView diputadoImage = (ImageView) findViewById(R.id.diputado_image);
        TextView eleccionView = (TextView) findViewById(R.id.diputado_eleccion);
        TextView entidadView = (TextView) findViewById(R.id.diputado_entidad);
        TextView circunView = (TextView) findViewById(R.id.diputado_circunscripcion);
        TextView curulView = (TextView) findViewById(R.id.diputado_curul);
        TextView emailView = (TextView) findViewById(R.id.diputado_email);

        nameView.setText(diputado.getString(ParseConstants.KEY_NAME));
        Picasso.with(this).load(diputado.getString(ParseConstants.KEY_FOTO)).into(diputadoImage);
        eleccionView.setText(diputado.getString(ParseConstants.KEY_TIPO_ELECCION));
        entidadView.setText(diputado.getString(ParseConstants.KEY_ENTIDAD));
        circunView.setText(diputado.getString(ParseConstants.KEY_CIRCUNSCRIPCION));
        curulView.setText(diputado.getString(ParseConstants.KEY_CURUL));
        emailView.setText(diputado.getString(ParseConstants.KEY_CORREO));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diputado, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
