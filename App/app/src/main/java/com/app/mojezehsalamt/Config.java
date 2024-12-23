package com.app.mojezehsalamt;

public class Config {

    //your admin panel url here
    public static final String SERVER_URL = "https://labu.co.com/mojezehsalamt/app";

    //set true to enable ads or set false to disable ads
    public static final boolean ENABLE_ADMOB_BANNER_ADS = false;
    public static final boolean ENABLE_ADMOB_INTERSTITIAL_ADS = false;
    public static final int ADMOB_INTERSTITIAL_ADS_INTERVAL = 3;

    //set true to enable exit dialog or set false to disable exit dialog
    public static final boolean ENABLE_EXIT_DIALOG = true;

    //set true if you want to enable RTL (Right To Left) mode, e.g : Arabic Language
    public static final boolean ENABLE_RTL_MODE = true;

    //number of grid column of recipes grid
    public static final int NUM_OF_COLUMNS = 2;

    //limit for number of recent recipes
    public static final int NUM_OF_RECENT_RECIPES = 5500;

}
