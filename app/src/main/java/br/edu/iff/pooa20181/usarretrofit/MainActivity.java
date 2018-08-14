package br.edu.iff.pooa20181.usarretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.iff.pooa20181.usarretrofit.interfaceretrofit.InterfaceRetrofitGithub;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btUsuario = findViewById(R.id.btUsuario);

        btUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cria um obj do tipo da interface
                InterfaceRetrofitGithub githubUser = InterfaceRetrofitGithub.retrofit.create(InterfaceRetrofitGithub.class);
                //chama o método para obter o usuario(é um request)
                final Call<Usuario> call = githubUser.getUsuario("lglmoura");
                //responsavel por pegar o valor do retorno
                call.enqueue(new Callback<Usuario>()
                {

                    //é um response(obter resposta)
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        //verifica o código de retorno
                        int code = response.code();
                        //se foi codigo de sucesso
                        if (code == 200) {
                            //pega  nome do usuario
                            Usuario usuario = response.body();
                            //mostra mensagem
                            Toast.makeText(getBaseContext(), "Nome do usuário: " +
                                    usuario.name, Toast.LENGTH_LONG).show();

                        } else {
                            //se teve falha, mostra a mensagem de falha
                            Toast.makeText(getBaseContext(), "Falha: " + String.valueOf(code),

                                    Toast.LENGTH_LONG).show();

                        }

                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Toast.makeText(getBaseContext(), t.getMessage(),

                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
