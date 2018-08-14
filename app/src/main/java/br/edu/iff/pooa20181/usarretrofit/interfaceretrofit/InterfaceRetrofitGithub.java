package br.edu.iff.pooa20181.usarretrofit.interfaceretrofit;

import br.edu.iff.pooa20181.usarretrofit.Usuario;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfaceRetrofitGithub {
    @GET("/users/{usuario}")
    Call<Usuario> getUsuario(@Path("usuario") String usuario);
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
