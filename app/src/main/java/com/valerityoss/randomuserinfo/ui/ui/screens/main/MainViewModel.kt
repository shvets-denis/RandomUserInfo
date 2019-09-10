package com.valerityoss.randomuserinfo.ui.ui.screens.main


import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.valerityoss.randomuserinfo.ui.data.GitRepoRepository
import com.valerityoss.randomuserinfo.ui.ui.ui_model.Repository
import com.valerityoss.randomuserinfo.ui.utility.extensions.plusAssign
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(var gitRepoRepository: GitRepoRepository) : ViewModel() {

    val text = ObservableField("old data")
    val isLoading = ObservableField(false)
    var repositories = MutableLiveData<ArrayList<Repository>>()
    private val compositeDisposable = CompositeDisposable()

    fun loadRepositories() {
        isLoading.set(true)
        compositeDisposable += gitRepoRepository.getRepositories()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<ArrayList<Repository>>() {
                override fun onComplete() {
                    isLoading.set(false)
                }

                override fun onNext(data: ArrayList<Repository>) {
                    repositories.value = data
                }

                override fun onError(e: Throwable) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            })
    }

    override fun onCleared() {
        super.onCleared()
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }

}