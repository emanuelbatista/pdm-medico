package pdm.ifpb.edu.br.medico.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import pdm.ifpb.edu.br.medico.R;
import pdm.ifpb.edu.br.medico.adapter.ListComentarios;
import pdm.ifpb.edu.br.medico.entity.Cliente;
import pdm.ifpb.edu.br.medico.entity.Avaliacao;
import pdm.ifpb.edu.br.medico.entity.Medico;
import pdm.ifpb.edu.br.medico.util.InternalStore;
import pdm.ifpb.edu.br.medico.util.PathServidor;

public class ComentariosActivity extends AppCompatActivity {

    private Medico medico;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentarios);
        this.medico=(Medico)getIntent().getExtras().getSerializable("medico");
        //
        listView=(ListView)findViewById(R.id.list_comentarios);
        ListComentarios listComentarios=new ListComentarios(this,medico.getAvaliacoes());
        listView.setAdapter(listComentarios);

    }


    public void addAvaliacao(View v){
        Avaliacao avaliacao =new Avaliacao();
        EditText editText=(EditText)findViewById(R.id.text_avaliacao);
        RatingBar ratingBar=(RatingBar)findViewById(R.id.num_avaliacao);
        avaliacao.setCliente((Cliente) InternalStore.readObject(this, "cliente"));
        avaliacao.setOpiniao(editText.getEditableText().toString());
        avaliacao.setNota(new Float(ratingBar.getRating()).longValue());
        ratingBar.setRating(0);
        editText.setText(null);

        RestTemplate template=new RestTemplate();
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        medico=template.getForObject(PathServidor.PATH_SERVIDOR+"/medicos/{id}",Medico.class,medico.getId());
        medico.getAvaliacoes().add(avaliacao);
        //
        template.postForEntity(PathServidor.PATH_SERVIDOR + "/medicos/add", medico, Void.class);
//

        ListComentarios listComentarios=(ListComentarios)listView.getAdapter();
        listComentarios.getAvaliacaoList().add(avaliacao);
        listComentarios.notifyDataSetChanged();
    }
}
