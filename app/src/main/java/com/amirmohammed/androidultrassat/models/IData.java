package com.amirmohammed.androidultrassat.models;

public interface IData extends ITest, IThree {

    String URL = "google.com";

    void getData();

    default void test(){

    }

}
