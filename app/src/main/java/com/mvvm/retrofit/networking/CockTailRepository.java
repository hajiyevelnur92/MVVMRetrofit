package com.mvvm.retrofit.networking;

import androidx.lifecycle.MutableLiveData;
import com.mvvm.retrofit.model.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CockTailRepository {

    private static CockTailRepository cockTailRepository;

    public static CockTailRepository getInstance(){
        if (cockTailRepository == null){
            cockTailRepository = new CockTailRepository();
        }
        return cockTailRepository;
    }

    private CockTailApi cockTailApi;

    public CockTailRepository(){
        cockTailApi = RetrofitService.cteateService(CockTailApi.class);
    }

    public MutableLiveData<ServerResponse> getDrinks(String source){
        MutableLiveData<ServerResponse> drinkData = new MutableLiveData<>();
        cockTailApi.getCockTailList(source).enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call,
                                   Response<ServerResponse> response) {
                if (response.isSuccessful()){
                    drinkData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                drinkData.setValue(null);
            }
        });
        return drinkData;
    }
}
