package com.example.b07projectapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.b07projectapplication.ui.login.LoginActivity;
import com.example.b07projectapplication.ui.login.LoginContract;
import com.example.b07projectapplication.ui.login.LoginModel;
import com.example.b07projectapplication.ui.login.LoginPresenter;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    LoginActivity view;

    @Mock
    LoginModel model;

    @Test
    public void testPresenter_loginError(){
        when(view.getEmail()).thenReturn("");
        when(view.getPassword()).thenReturn("123456");
        view.validateLogin();

        LoginPresenter presenter = new LoginPresenter(view, model);
        model.setPresenter(presenter);
        presenter.checkInput();

        presenter.checkError("Login error");
        verify(view).displayError("Login Failed, Please Check Your Email and Password!");
    }

    @Test
    public void testPresenter_DatabaseError(){
        when(view.getEmail()).thenReturn("user@gmail.com");
        when(view.getPassword()).thenReturn("123456");
        view.validateLogin();

        LoginPresenter presenter = new LoginPresenter(view, model);
        model.setPresenter(presenter);
        presenter.checkInput();

        presenter.checkError("Database error");
        verify(view).displayError("Database error!");

    }

    @Test
    public void testPresenter_isOwner(){
        when(view.getEmail()).thenReturn("user@gmail.com");
        when(view.getPassword()).thenReturn("123456");
        view.validateLogin();
        boolean isOwner = true;

        LoginPresenter presenter = new LoginPresenter(view, model);
        model.setPresenter(presenter);
        presenter.checkInput();

        presenter.successfulLogin(isOwner);
        verify(view).displayMessage(isOwner);
    }

    @Test
    public void testPresenter_isNotOwner(){
        when(view.getEmail()).thenReturn("user@gmail.com");
        when(view.getPassword()).thenReturn("123456");
        view.validateLogin();
        boolean isOwner = false;

        LoginPresenter presenter = new LoginPresenter(view, model);
        model.setPresenter(presenter);
        presenter.checkInput();

        presenter.successfulLogin(isOwner);
        verify(view).displayMessage(isOwner);
    }
}