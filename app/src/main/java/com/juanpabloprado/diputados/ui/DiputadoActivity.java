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
        Log.i(TAG, "diputadoId " + diputadoId);
        Toast.makeText(this, "diputadoId " + diputadoId, Toast.LENGTH_LONG).show();

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
        nameView.setText(diputado.getString(ParseConstants.KEY_NAME));
        ImageView diputadoImage = (ImageView) findViewById(R.id.diputado_image);
        Picasso.with(this).load(diputado.getString(ParseConstants.KEY_FOTO)).into(diputadoImage);
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
