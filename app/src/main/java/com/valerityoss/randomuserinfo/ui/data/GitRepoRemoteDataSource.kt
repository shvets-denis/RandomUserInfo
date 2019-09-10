package com.valerityoss.randomuserinfo.ui.data

import com.valerityoss.randomuserinfo.ui.ui.ui_model.Repository
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class GitRepoRemoteDataSource {
    fun getRepositories(): Observable<ArrayList<Repository>> {
        var arrayList = ArrayList<Repository>()
        with(arrayList) {
            add(
                Repository(
                    "First from remote",
                    "Owner remote 1",
                    100,
                    false
                )
            )
            add(
                Repository(
                    "Second from remote",
                    "Owner remote 2",
                    30,
                    true
                )
            )
            add(
                Repository(
                    "Third from remote",
                    "Owner remote 3",
                    430,
                    false
                )
            )
        }
        return Observable.just(arrayList).delay(2, TimeUnit.SECONDS)
    }
}