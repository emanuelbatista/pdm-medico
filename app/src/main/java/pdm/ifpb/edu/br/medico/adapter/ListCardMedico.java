package pdm.ifpb.edu.br.medico.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import pdm.ifpb.edu.br.medico.R;
import pdm.ifpb.edu.br.medico.entity.Avaliacao;
import pdm.ifpb.edu.br.medico.entity.Medico;

/**
 * Created by emanuel on 23/03/16.
 */
public class ListCardMedico extends ArrayAdapter<Medico> {

    private LayoutInflater layoutInflater;
    private List<Medico> medicoList;

    public ListCardMedico(Context context, List<Medico> objects) {
        super(context, 0, objects);
        this.medicoList=objects;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Medico medico=getItem(position);
        //
        convertView=layoutInflater.inflate(R.layout.lista_medico,null);
        TextView nomeMedicoTextView=(TextView)convertView.findViewById(R.id.value_nome_medico);
        nomeMedicoTextView.setText(medico.getNome());
        //
        TextView nomeClinicaTextView=(TextView)convertView.findViewById(R.id.value_nome_clinica);
        nomeClinicaTextView.setText(medico.getNomeClinica());

        return convertView;
    }

    public List<Medico> getList(){
        return medicoList;
    }

    public void setMedicoList(List<Medico> medicoList) {
        this.medicoList = medicoList;
        super.clear();
        for (Medico a : medicoList) {
            super.add(a);
        }
    }
}
