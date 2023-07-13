package com.example.movieapp.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.domain.error.ErrorEntity
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        observeFlow()
    }

    private fun observeFlow() {
        viewModel.loginResult
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { handleLoginResult(it) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    // This is VIEW LOGIC
    private fun handleLoginResult(loginResult: LoginResult) {
        when (loginResult) {
            is LoginResult.LoginSuccess -> showMessage(
                getString(
                    R.string.user_login_success,
                    loginResult.userEntity.userName,
                ),
            )

            is LoginResult.LoginFail -> {
                when (loginResult.errorEntity) {
                    is ErrorEntity.UserNameValidateErrorEntity -> showMessage(getString(R.string.user_name_is_wrong))
                    is ErrorEntity.PasswordValidateErrorEntity -> showMessage(getString(R.string.password_is_wrong))
                    else -> showMessage(
                        loginResult.errorEntity.message ?: getString(R.string.unknown_message),
                    )
                }
            }
        }
    }

    private fun setupView() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonLogin.setOnClickListener {
            viewModel.login()
        }

        binding.editUsername.doAfterTextChanged {
            viewModel.setUserName(it.toString())
        }

        binding.editPassword.doAfterTextChanged {
            viewModel.setPassword(it.toString())
        }
    }

    private fun showMessage(message: String) {
        binding.textError.text = message
        binding.textError.isVisible = message.isNotEmpty()
    }
}
