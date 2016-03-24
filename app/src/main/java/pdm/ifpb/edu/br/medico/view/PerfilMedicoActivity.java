
package pdm.ifpb.edu.br.medico.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import pdm.ifpb.edu.br.medico.R;
import pdm.ifpb.edu.br.medico.entity.Medico;

public class PerfilMedicoActivity extends AppCompatActivity {

    private Medico medico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_medico);
        this.medico=(Medico)getIntent().getSerializableExtra("medico");
        addValoresAosCampos();
    }


    public void addValoresAosCampos(){
        TextView textView=(TextView)findViewById(R.id.nome_medico);
        textView.setText(medico.getNome());
        textView=(TextView)findViewById(R.id.clinica_medico);
        textView.setText(medico.getNomeClinica());
        textView=(TextView)findViewById(R.id.especialidade_medico);
        textView.setText(medico.getEspecialidade());
        textView=(TextView)findViewById(R.id.contato_medico);
        textView.setText(medico.getContato());
        textView=(TextView)findViewById(R.id.cidade_medico);
        textView.setText(medico.getCidade());
        textView=(TextView)findViewById(R.id.estado_medico);
        textView.setText(medico.getEstado());
        textView=(TextView)findViewById(R.id.especialidade_medico);
        textView.setText(medico.getEspecialidade());
        textView=(TextView)findViewById(R.id.opiniao_medico);
        textView.setText(medico.getOpiniao());

    }

    public void openMap(View v){
        Intent intent=new Intent(this,MapActivity.class);
        intent.putExtra("lat", medico.getLat());
        intent.putExtra("log",medico.getLog());
        startActivity(intent);
    }

    public void openComentarios(View v){
        Intent intent=new Intent(this,ComentariosActivity.class);
        intent.putExtra("medico",medico);
        startActivity(intent);
    }

}
