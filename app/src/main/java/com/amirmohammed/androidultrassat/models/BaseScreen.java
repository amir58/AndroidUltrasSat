package com.amirmohammed.androidultrassat.models;

public abstract class BaseScreen {

    public BaseScreen(String screenName) {
        System.out.println(screenName);
    }

    public final boolean isWifiEnable() {
        return true;
    }

    protected  boolean isDataEnable() {
        return true;
    }

    protected abstract void startLoading();


    protected void stopLoading() {
    }

    protected void navigateToLogin() {
    }

}
