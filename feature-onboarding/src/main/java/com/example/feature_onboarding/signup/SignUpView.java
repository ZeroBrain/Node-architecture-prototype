package com.example.feature_onboarding.signup;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.Toast;

import com.example.feature_onboarding.R;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by lev.novikov
 * Date: 30/11/17.
 */

public class SignUpView extends ConstraintLayout implements SignUpPresenter {

    static final int layout = R.layout.onboarding_signup;

    public SignUpView(Context context) {
        super(context);
    }

    public SignUpView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SignUpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private PublishSubject<String> facebookClickSubject = PublishSubject.create();
    private PublishSubject<String> googleClickSubject = PublishSubject.create();
    private PublishSubject<String> signUpClickSubject = PublishSubject.create();

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        final EditText phoneNumber = findViewById(R.id.phone);
        findViewById(R.id.facebook).setOnClickListener(v -> facebookClickSubject.onNext(phoneNumber.getText().toString()));
        findViewById(R.id.google).setOnClickListener(v -> googleClickSubject.onNext(phoneNumber.getText().toString()));
        findViewById(R.id.sign_up).setOnClickListener(v -> signUpClickSubject.onNext(phoneNumber.getText().toString()));
    }

    @Override
    public void showProgress() {
        //TODO
    }

    @Override
    public void hideProgress() {
        //TODO
    }

    @Override
    public void showMessage(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public PublishSubject<String> getFacebookClickStream() {
        return facebookClickSubject;
    }

    @Override
    public PublishSubject<String> getGoogleClickStream() {
        return googleClickSubject;
    }

    @Override
    public PublishSubject<String> getSignUpClickStream() {
        return signUpClickSubject;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        facebookClickSubject.onComplete();
        googleClickSubject.onComplete();
        signUpClickSubject.onComplete();
    }
}