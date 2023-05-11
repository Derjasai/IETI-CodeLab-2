package org.adaschool.retrofit;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import org.adaschool.retrofit.databinding.ActivityMainBinding;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String breed = "akita";
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DogApiService dogApiService = RetrofitInstance.getRetrofitInstance().create(DogApiService.class);

        Call<RandomDto> call = dogApiService.getRandomBreed(breed);
        call.enqueue(new Callback<RandomDto>() {
            @Override
            public void onResponse(Call<RandomDto> call, Response<RandomDto> response) {
                if (response.isSuccessful()) {
                    loadDogInfo(response.body().getMessage());
                } else {
                    Log.e(TAG, "Error en la respuesta de la API");
                }
            }

            @Override
            public void onFailure(Call<RandomDto> call, Throwable t) {
                Log.e(TAG, "Error al llamar a la API", t);
            }
        });

    }

    private void loadDogInfo(String url) {
        String dogImageUrl = url;
        String dogName = "Chesapeake Retriever";
        binding.textView.setText(dogName);
        Glide.with(this)
                .load(dogImageUrl)
                .into(binding.imageView);
    }


}
