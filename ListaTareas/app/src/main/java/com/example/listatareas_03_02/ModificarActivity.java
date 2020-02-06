package com.example.listatareas_03_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ModificarActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText etNombre,etLugar,etDescripcion;
    private TextView tvTitulo;
    private Spinner sp;
    ArrayAdapter<CharSequence> adapter;
    private int rowid,importancia;
    String nombre,lugar,descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);
        cargarViews();
        sp.setOnItemSelectedListener(this);
        adapter=ArrayAdapter.createFromResource(this,R.array.arrayImp,android.R.layout.simple_spinner_item);
        sp.setAdapter(adapter);
        cargarDatos();
    }

    private void cargarDatos() {
        Bundle datos=new Bundle();
        datos=getIntent().getExtras();
        rowid=datos.getInt(MainActivity.ROWID);
        nombre=datos.getString(MainActivity.NOMBRE);
        lugar=datos.getString(MainActivity.LUGAR);
        descripcion=datos.getString(MainActivity.DESCRIPCION);
        tvTitulo.setText(String.format(getResources().getString(R.string.titulo),rowid));
        etNombre.setText(""+nombre);
        etLugar.setText(""+lugar);
        etDescripcion.setText(""+descripcion);
    }

    private void cargarViews() {
        etNombre=findViewById(R.id.etMNombre);
        etLugar=findViewById(R.id.etMLugar);
        etDescripcion=findViewById(R.id.etMDescripcion);
        sp=findViewById(R.id.spinner2);
        tvTitulo=findViewById(R.id.tvMTitulo);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String op=adapter.getItem(position).toString();
        switch (op){
            case "Baja":
                importancia=1;
                break;
            case "Media":
                importancia=2;
                break;
            default:
                importancia=3;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void volver(View v){
        Intent i = new Intent();
        Bundle datos=new Bundle();
        datos.putInt(MainActivity.ROWID,rowid);
        datos.putString(MainActivity.NOMBRE,nombre);
        datos.putString(MainActivity.LUGAR,lugar);
        datos.putString(MainActivity.DESCRIPCION,descripcion);
        datos.putInt(MainActivity.IMPORTANCIA,importancia);
        i.putExtras(datos);
        setResult(RESULT_OK, i);
        finish();

    }

    public void modificar(View v){
        if(etNombre.getText().toString().trim().length()==0||etLugar.getText().toString().trim().length()==0
        ||etDescripcion.getText().toString().trim().length()==0){
            Toast.makeText(this,"Rellene los campos",Toast.LENGTH_LONG).show();
        }
        BaseDatosHelper conecion=new BaseDatosHelper(this,MainActivity.BASE,null,1);
        SQLiteDatabase base=conecion.getWritableDatabase();
        nombre=etNombre.getText().toString();
        lugar=etLugar.getText().toString();
        descripcion=etDescripcion.getText().toString();
        String rowid2=String.valueOf(rowid);
        ContentValues registro= new ContentValues();
        registro.put(BaseDatosHelper.CAMPO1,nombre);
        registro.put(BaseDatosHelper.CAMPO2,lugar);
        registro.put(BaseDatosHelper.CAMPO3,descripcion);
        registro.put(BaseDatosHelper.CAMPO4,importancia);
        base.update(BaseDatosHelper.TABLA, registro,"ROWID=?",new String[] {rowid2});
        base.close();
    }
}
