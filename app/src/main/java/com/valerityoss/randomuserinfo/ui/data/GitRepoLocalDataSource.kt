package com.valerityoss.randomuserinfo.ui.data

import com.valerityoss.randomuserinfo.ui.ui.ui_model.Repository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class GitRepoLocalDataSource {
    fun getRepositories(): Observable<ArrayList<Repository>> {
        var arrayList = ArrayList<Repository>()
        with(arrayList) {
            add(
                Repository(
                    "First From Local",
                    "Owner Local 1",
                    100,
                    false
                )
            )
            add(
                Repository(
                    "Second From Local",
                    "Owner Local 2",
                    30,
                    true
                )
            )
            add(
                Repository(
                    "Third From Local",
                    "Owner Local 3",
                    430,
                    false
                )
            )
        }
        return Observable.just(arrayList).delay(2, TimeUnit.SECONDS)
    }

    fun saveRepositories(arrayList: ArrayList<Repository>): Completable {
        return Single.just(1).delay(1, TimeUnit.SECONDS).toCompletable()
    }
}