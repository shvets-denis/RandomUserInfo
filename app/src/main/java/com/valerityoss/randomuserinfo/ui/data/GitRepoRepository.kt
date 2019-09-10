package com.valerityoss.randomuserinfo.ui.data

import com.valerityoss.randomuserinfo.ui.android_managers.NetManager
import com.valerityoss.randomuserinfo.ui.ui.ui_model.Repository
import io.reactivex.Observable
import javax.inject.Inject

class GitRepoRepository @Inject constructor(val netManager: NetManager) {
    val localDataSource = GitRepoLocalDataSource()
    val remoteDataSource = GitRepoRemoteDataSource()
    fun getRepositories(): Observable<ArrayList<Repository>> {
        netManager.isConnectedToInternet?.let {
            if (it) {
                //todo save those data to local data store
                return remoteDataSource.getRepositories().flatMap {
                    return@flatMap localDataSource
                        .saveRepositories(it)
                        .toSingleDefault(it)
                        .toObservable()
                }
            }
        }
        return localDataSource.getRepositories()
    }
}