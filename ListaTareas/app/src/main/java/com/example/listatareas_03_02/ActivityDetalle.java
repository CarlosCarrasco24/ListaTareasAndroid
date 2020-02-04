package com.example.listatareas_03_02;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityDetalle extends AppCompatActivity {
    private TextView tvNombre,tvLugar,tvTitulo,tvDescripcion,tvImpor;
    private ImageView iv;
    private int [] dibujar={R.drawable.ic_green,R.drawable.ic_yellow,R.drawable.ic_red};
    private int rowid,importancia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        cargarViews();
        //cargo datos del intent
        Bundle datos=new Bundle();
        datos=getIntent().getExtras();
        rowid=datos.getInt(MainActivity.ROWID);
        rellenarDatos();
    }

    private void rellenarDatos() {
        BaseDatosHelper conecion=new BaseDatosHelper(this,MainActivity.BASE,null,1);
        SQLiteDatabase base=conecion.getReadableDatabase();
        Cursor c=base.rawQuery("select ROWID, nombre, lugar, descripcion, importancia " +
                " from "+BaseDatosHelper.TABLA+" where ROWID="+rowid,null);
        if(c.moveToFirst()){
            tvNombre.setText(c.getString(1));
            tvLugar.setText(c.getString(2));
            tvDescripcion.setText(c.getString(3));
            importancia=c.getInt(4);
            tvTitulo.setText(String.format(getResources().getString(R.string.titulo),rowid));
        }
        base.close();
        switch (importancia){
            case 1:
                tvImpor.setText("Baja");
                iv.setImageResource(dibujar[0]);
                break;
            case 2:
                tvImpor.setText("Media");
                iv.setImageResource(dibujar[1]);
                break;
             default:
                tvImpor.setText("Alta");
                iv.setImageResource(dibujar[2]);
        }
    }

    private void cargarViews(){
        tvTitulo=findViewById(R.id.tvTitulo);
        tvNombre=findViewById(R.id.tvDNombre);
        tvLugar=findViewById(R.id.tvDLugar);
        tvDescripcion=findViewById(R.id.tvDDescrip);
        tvImpor=findViewById(R.id.tvDImpor);
        iv=findViewById(R.id.ivDImport);
    }

    public void volver(View v){
        finish();
    }
}
