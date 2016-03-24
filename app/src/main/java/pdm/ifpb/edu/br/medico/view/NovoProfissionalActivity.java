package pdm.ifpb.edu.br.medico.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import pdm.ifpb.edu.br.medico.R;
import pdm.ifpb.edu.br.medico.entity.Medico;
import pdm.ifpb.edu.br.medico.util.PathServidor;

public class NovoProfissionalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Novo Médico");
        setContentView(R.layout.activity_novo_profissional);
    }

    public void salvarMedico(View v){
        Medico medico=new Medico();
        TextView textView=(TextView)findViewById(R.id.field_nome);
        medico.setNome(textView.getText().toString());
        textView=(TextView)findViewById(R.id.field_cidade);
        medico.setCidade(textView.getText().toString());
        textView=(TextView)findViewById(R.id.field_estado);
        medico.setEstado(textView.getText().toString());
        textView=(TextView)findViewById(R.id.field_contato);
        medico.setContato(textView.getText().toString());
        textView=(TextView)findViewById(R.id.field_especialidade);
        medico.setEspecialidade(textView.getText().toString());
        textView=(TextView)findViewById(R.id.field_lat);
        medico.setLat(textView.getText().toString());
        textView=(TextView)findViewById(R.id.field_log);
        medico.setLog(textView.getText().toString());
        textView=(TextView)findViewById(R.id.field_nomeClinica);
        medico.setNomeClinica(textView.getText().toString());
        textView=(TextView)findViewById(R.id.field_opiniao);
        medico.setOpiniao(textView.getText().toString());
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        restTemplate.postForEntity(PathServidor.PATH_SERVIDOR + "/medicos/add/body", medico, Void.class);

        Intent intent=new Intent(this,ListarProfissionaisActivity.class);
        startActivity(intent);

    }


}
