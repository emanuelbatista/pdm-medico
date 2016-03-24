package pdm.ifpb.edu.br.medico.view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import pdm.ifpb.edu.br.medico.R;

public class MapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        final String lat=getIntent().getStringExtra("lat");
        final String log=getIntent().getStringExtra("log");

        AsyncTask<Void, Void, Bitmap> setImageFromUrl = new AsyncTask<Void, Void, Bitmap>(){
            @Override
            protected Bitmap doInBackground(Void... params) {
                RestTemplate restTemplate=new RestTemplate();
                restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
                ResponseEntity<byte[]> responseEntity=restTemplate.exchange("http://maps.google.com/maps/api/staticmap?center={lat},{log}&zoom=15&sensor=false&size=1000x1000", HttpMethod.GET, HttpEntity.EMPTY,byte[].class,lat,log);

                if(responseEntity.getStatusCode() == HttpStatus.ACCEPTED || responseEntity.getStatusCode() == HttpStatus.OK) {
                     byte[] image=responseEntity.getBody();
                    return BitmapFactory.decodeByteArray(image,0,image.length);
                    //
                }
                return null;
            }
            protected void onPostExecute(Bitmap bmp) {
                if(bmp!=null) {
                    ImageView imageView=(ImageView)findViewById(R.id.image_map);
                    imageView.setImageBitmap(bmp);
                }else{
                    Toast.makeText(MapActivity.this, "Sem Imagem", Toast.LENGTH_SHORT).show();
                }
            }
        };
        setImageFromUrl.execute();
    }

}
