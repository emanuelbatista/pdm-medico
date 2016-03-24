package pdm.ifpb.edu.br.medico.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import pdm.ifpb.edu.br.medico.R;
import pdm.ifpb.edu.br.medico.entity.Avaliacao;

/**
 * Created by emanuel on 23/03/16.
 */
public class ListComentarios extends ArrayAdapter<Avaliacao> {

    private LayoutInflater layoutInflater;
    private List<Avaliacao> avaliacaoList;

    public ListComentarios(Context context, List<Avaliacao> objects) {
        super(context, 0, objects);
        this.avaliacaoList = objects;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Avaliacao avaliacao = getItem(position);
        convertView = layoutInflater.inflate(R.layout.list_comentario, null);
        TextView textView = (TextView) convertView.findViewById(R.id.value_usuario);
        textView.setText(avaliacao.getCliente().getEmail());
        textView = (TextView) convertView.findViewById(R.id.value_comentario);
        textView.setText(avaliacao.getOpiniao());
        RatingBar ratingBar = (RatingBar) convertView.findViewById(R.id.value_avaliacao);
        ratingBar.setRating(avaliacao.getNota());
        return convertView;
    }


    public List<Avaliacao> getAvaliacaoList() {
        return avaliacaoList;
    }

    public void setAvaliacaoList(List<Avaliacao> avaliacaoList) {
        this.avaliacaoList = avaliacaoList;

    }
}
