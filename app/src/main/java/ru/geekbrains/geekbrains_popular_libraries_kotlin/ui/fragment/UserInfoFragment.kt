package ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.databinding.FragmentUserInfoBinding
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.model.entity.GithubUser
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.presenter.UserInfoPresenter
import ru.geekbrains.geekbrains_popular_libraries_kotlin.mvp.view.UserInfoView
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.App
import ru.geekbrains.geekbrains_popular_libraries_kotlin.ui.BackClickListener

const val USER: String = "user"

class UserInfoFragment : MvpAppCompatFragment(), UserInfoView, BackClickListener {
    private var user: GithubUser? = null

    private val presenter by moxyPresenter {
        arguments?.let {
            user = it.getParcelable(USER)
        }
        UserInfoPresenter(user, App.instance.router)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable(USER)
        }
    }

    private var vb: FragmentUserInfoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentUserInfoBinding.inflate(inflater, container, false).also {
        vb = it
    }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }


    override fun backPressed() = presenter.backClick()

    override fun updateInfo(userLogin: String) {
        vb?.tvLogin?.text = userLogin
    }

    companion object {
        @JvmStatic
        fun newInstance(user: GithubUser) =
            UserInfoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(USER, user)
                }
            }
    }
}