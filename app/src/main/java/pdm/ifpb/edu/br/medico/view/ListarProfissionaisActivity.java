package pdm.ifpb.edu.br.medico.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import pdm.ifpb.edu.br.medico.MainActivity;
import pdm.ifpb.edu.br.medico.R;
import pdm.ifpb.edu.br.medico.adapter.ListCardMedico;
import pdm.ifpb.edu.br.medico.entity.Medico;
import pdm.ifpb.edu.br.medico.util.PathServidor;


public class ListarProfissionaisActivity extends AppCompatActivity {

    private ListView medicosListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_medico);
        setTitle("Médicos");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListarProfissionaisActivity.this,NovoProfissionalActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_list_funcionario, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        mountList();
    }

    private void mountList(){
        RestTemplate restTemplate=new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        //
        ResponseEntity<Medico[]> responseEntity=restTemplate.getForEntity(PathServidor.PATH_SERVIDOR + "/medicos",Medico[].class);

        ListCardMedico listCardMedico=new ListCardMedico(this, Arrays.asList(responseEntity.getBody()));
        ListView medicosListView=(ListView)findViewById(R.id.medico_list_view);
        medicosListView.setAdapter(listCardMedico);
        medicosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Medico medico = (Medico) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListarProfissionaisActivity.this, PerfilMedicoActivity.class);
                intent.putExtra("medico", medico);
                startActivity(intent);
            }
        });
        this.medicosListView=medicosListView;
    }

    public void pesquisar(View v){

        EditText editText=(EditText)findViewById(R.id.text_busca);
        RestTemplate template=new RestTemplate();
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Medico[] medicoList=template.getForObject(PathServidor.PATH_SERVIDOR+"/medicos/busca?query={query}",Medico[].class,editText.getText().toString());
        Log.i("medico", String.valueOf(medicoList.length));
        medicosListView.setAdapter(new ListCardMedico(this,Arrays.asList(medicoList)));


    }

    public void logout(View v){
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
