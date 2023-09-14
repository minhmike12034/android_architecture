package com.example.movieapp.fragment.login

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.domain.usecase.login.LoginUseCase
import com.example.movieapp.R
import com.example.movieapp.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class LoginFragmentTest {

    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var loginUseCase: LoginUseCase

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun loginSuccess() {
        launchFragmentInHiltContainer<LoginFragment>()

        onView(withId(R.id.edit_username))
            .perform(typeText("test"))

        onView(withId(R.id.edit_password))
            .perform(typeText("test"))

        onView(withId(R.id.button_login))
            .perform(click())

        onView(withId(R.id.text_error))
            .check(matches(isDisplayed()))
    }

    @Test
    fun invalidUserName() {
        launchFragmentInHiltContainer<LoginFragment>()

        val expectedText = ApplicationProvider.getApplicationContext<Context>()
            .getString(R.string.username_validation_error)

        onView(withId(R.id.button_login))
            .perform(click())

        onView(withId(R.id.text_error))
            .check(matches(isDisplayed()))

        onView(withId(R.id.text_error))
            .check(matches(ViewMatchers.withText(expectedText)))
    }

    @Test
    fun invalidPassword() {
        launchFragmentInHiltContainer<LoginFragment>()

        val expectedText = ApplicationProvider.getApplicationContext<Context>()
            .getString(R.string.password_validation_error)

        onView(withId(R.id.edit_username))
            .perform(typeText("admin"))

        onView(withId(R.id.button_login))
            .perform(click())

        onView(withId(R.id.text_error))
            .check(matches(isDisplayed()))

        onView(withId(R.id.text_error))
            .check(matches(ViewMatchers.withText(expectedText)))
    }

    @Test
    fun wrongUserNameAndPassword() {
        launchFragmentInHiltContainer<LoginFragment>()

        onView(withId(R.id.edit_username))
            .perform(typeText("minh"))

        onView(withId(R.id.edit_password))
            .perform(typeText("1234"))

        onView(withId(R.id.button_login))
            .perform(click())

        onView(withId(R.id.text_error))
            .check(matches(isDisplayed()))
    }
}
