package br.edu.ifsc.sqllite_crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AlunoAdapter extends ArrayAdapter<Aluno> {
    private static  final String TAG = "AlunoAdapter";

    Context mContext;
    int mResouce ; //recurso Template Item Lista
    public AlunoAdapter(@NonNull Context context, int resource, @NonNull List<Aluno> objects) {
        super(context, resource, objects);
        mContext = context;
        mResouce= resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView= inflater.inflate(mResouce,parent, false);
        TextView tvCodigo = convertView.findViewById(R.id.tvCodigo);
        TextView tvNomeUC = convertView.findViewById(R.id.tvNomeUC);
        TextView tvNota   = convertView.findViewById(R.id.tvNota);
        Aluno aluno= getItem(position);
        tvCodigo.setText(Integer.toString(aluno.getCodigo()));
        tvNomeUC.setText(aluno.getNome_UC()) ;
        tvNota.setText(Double.toString(aluno.getNota())) ;
        return convertView;
    }
}
