package br.edu.ifsc.sqllite_crud;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;


public class ListaAlunos extends AppCompatActivity {
    ListView lv ;
    SQLiteDatabase bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        lv = findViewById(R.id.listView);
        bd = openOrCreateDatabase("alunos" , MODE_PRIVATE, null);
        montaListagem();
    }

    public  void montaListagem(){
        Cursor registros = bd.query("alunos", null , null , null, null , null , null);
        registros.moveToFirst();
        //*  Trecho de código utilizado para  Listar apenas uma informação por item */

        ArrayList<Aluno> itens = new ArrayList<>();

        do {
            int codigo = registros.getInt(registros.getColumnIndex("id"));
            String nome_uc = registros.getString(registros.getColumnIndex("nome_UC"));
            Double nota = registros.getDouble(registros.getColumnIndex("nota"));
            itens.add (new Aluno(codigo, nome_uc, nota));
        } while (registros.moveToNext());

        AlunoAdapter adapter = new AlunoAdapter(this, R.layout.template_lista_aluno,itens);

        lv.setAdapter(adapter);
    }
}