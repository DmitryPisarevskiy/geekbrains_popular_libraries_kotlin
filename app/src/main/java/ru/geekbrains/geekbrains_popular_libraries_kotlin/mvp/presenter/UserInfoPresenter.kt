package ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.GithubUsersRepo
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.list.IUsersListPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UserInfoView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UsersView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.list.IUserItemView

class UserInfoPresenter(var user: GithubUser?, val router: Router) :
    MvpPresenter<UserInfoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.updateInfo(user?.login ?: "")
    }

    fun backClick(): Boolean {
        router.exit()
        return true
    }
}