package com.mvvm.retrofit.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mvvm.retrofit.model.ServerResponse;
import com.mvvm.retrofit.networking.CockTailRepository;


public class CockTailViewModel extends ViewModel {

    private MutableLiveData<ServerResponse> mutableLiveData;
    private CockTailRepository cockTailRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        cockTailRepository = CockTailRepository.getInstance();
        mutableLiveData = cockTailRepository.getDrinks("Alcoholic");

    }

    public LiveData<ServerResponse> getCockTailRepository() {
        return mutableLiveData;
    }

}
