package pdm.ifpb.edu.br.medico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import pdm.ifpb.edu.br.medico.entity.Cliente;
import pdm.ifpb.edu.br.medico.util.InternalStore;
import pdm.ifpb.edu.br.medico.util.PathServidor;
import pdm.ifpb.edu.br.medico.view.ListarProfissionaisActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Login");
        setContentView(R.layout.activity_main);

    }

    public void clickButtonEntrar(View v){
        RestTemplate template=new RestTemplate();
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String email=((EditText)findViewById(R.id.email)).getText().toString();
        String senha=((EditText)findViewById(R.id.senha)).getText().toString();
        Cliente clienteRequest=new Cliente();
        clienteRequest.setEmail(email);
        clienteRequest.setSenha(senha);
        //
        Cliente cliente=template.getForObject(PathServidor.PATH_SERVIDOR + "/clientes/login?email={1}&senha={2}", Cliente.class, email, senha);
        if(cliente==null || cliente.getEmail()==null){
            Toast.makeText(this,"Login Inválido",Toast.LENGTH_LONG).show();
            return;
        }
        InternalStore.writeObject(this,"cliente",cliente);
        Intent intent=new Intent(this,ListarProfissionaisActivity.class);
        startActivity(intent);
    }


    public void clickButtonCadastrar(View v){
        RestTemplate template=new RestTemplate();
        template.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        String email=((EditText)findViewById(R.id.email)).getText().toString();
        String senha=((EditText)findViewById(R.id.senha)).getText().toString();
        Cliente clienteRequest=new Cliente();
        clienteRequest.setEmail(email);
        clienteRequest.setSenha(senha);
        //
        template.postForObject(PathServidor.PATH_SERVIDOR + "/clientes/salvar", clienteRequest, String.class);

        Intent intent=new Intent(this,ListarProfissionaisActivity.class);
        startActivity(intent);
    }


}
