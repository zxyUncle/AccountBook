package com.zxy.zxymvp.mvp

/**
 * Created by zxy on 2020/6/9 0009 18:13
 * ******************************************
 * *
 * ******************************************
 */
interface IPresenter<out View: IMvpView<IPresenter<View>>>: ILifecycle {
    val view:View
}

interface IMvpView<out Presenter: IPresenter<IMvpView<Presenter>>>: ILifecycle {
    val presenter:Presenter
}