package org.adaschool.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DogApiService {
    @GET("api/breeds/list/all")
    Call<BreedsListDto> getAllBreeds();

    @GET("api/breeds/image/random")
    Call<RandomDto> getRandomBreeds();

    @GET("api/breed/{raza}/images/random")
    Call<RandomDto> getRandomBreed(@Path("raza") String raza);

}