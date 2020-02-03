package com.example.listatareas_03_02;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int REQ_CODE=123;
    private RecyclerView recView;
    private ArrayList<ListaItem>miLista;
    ListaAdapter adapter;
    public static final String BASE="baseLista";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //se pone mainActivity.thisporque es una llamada anonima
                Intent i=new Intent(MainActivity.this,ActivityCrear.class);
                startActivityForResult(i,REQ_CODE);
            }
        });
        //******************************
        recView=findViewById(R.id.recView);
        obtenerRegistros();
    }

    private void obtenerRegistros() {
        miLista=new ArrayList<>();
        //ponemos el layout en recView
        recView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        adapter=new ListaAdapter(miLista);
        recView.setAdapter(adapter);
        //ahora leo todos los registros y los añado al arrayList miLista
        BaseDatosHelper conecion=new BaseDatosHelper(this,BASE,null,1);
        SQLiteDatabase base=conecion.getReadableDatabase();
        //dejar espacios que sino da error
        Cursor cursor=base.rawQuery("select ROWID, nombre, lugar, descripcion, importancia" +
                " from "+BaseDatosHelper.TABLA+" order by importancia desc",null);
        ListaItem item=null;
        while (cursor.moveToNext()){
            item=new ListaItem();
            item.setRowid(cursor.getInt(0));
            item.setNombre(cursor.getString(1));
            item.setLugar(cursor.getString(2));
            Log.d("CURSOR LUGAR",cursor.getString(2));
            item.setDescripcion(cursor.getString(3));
            item.setImportancia(cursor.getInt(4));
            miLista.add(item);
        }
        base.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ_CODE && resultCode==RESULT_OK){
            obtenerRegistros();
            Toast.makeText(this,"Tarea guardada con exito",Toast.LENGTH_LONG).show();
        }
    }
}
