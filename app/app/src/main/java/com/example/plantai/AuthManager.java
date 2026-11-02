package com.example.plantai;

import android.content.Context;
import android.content.SharedPreferences;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;
import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AuthManager {
    private static final String PREF_NAME = "KrishiDrishtiAuthPrefs";
    private static final String KEY_IS_LOGGED_IN = "is_logged_in";
    private static final String KEY_USER_NAME = "user_name";
    private static final String KEY_USER_PHONE = "user_phone";
    private static final String KEY_PASS_HASH = "pass_hash_";

    public static boolean isLoggedIn(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public static void setLoggedIn(Context context, boolean isLoggedIn, String name, String phone) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        if (name != null) editor.putString(KEY_USER_NAME, name);
        if (phone != null) editor.putString(KEY_USER_PHONE, phone);
        editor.apply();
    }

    public static String getUserName(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_USER_NAME, "Farmer");
    }

    public static String getUserPhone(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_USER_PHONE, "");
    }

    public static boolean registerUser(Context context, String name, String phone, String password) {
        if (phone == null || phone.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return false;
        }
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String hash = hashPassword(password);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_PASS_HASH + phone.trim(), hash);
        editor.putString("name_" + phone.trim(), name.trim());
        editor.apply();
        setLoggedIn(context, true, name.trim(), phone.trim());
        return true;
    }

    public static boolean loginUser(Context context, String phone, String password) {
        if (phone == null || password == null) return false;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String storedHash = prefs.getString(KEY_PASS_HASH + phone.trim(), null);
        if (storedHash == null) {
            // Default demo account for instant testing
            if ("9876543210".equals(phone.trim()) && "123456".equals(password.trim())) {
                setLoggedIn(context, true, "Farmer Friend", "9876543210");
                return true;
            }
            return false;
        }
        String inputHash = hashPassword(password);
        if (storedHash.equals(inputHash)) {
            String name = prefs.getString("name_" + phone.trim(), "Farmer");
            setLoggedIn(context, true, name, phone.trim());
            return true;
        }
        return false;
    }

    public static boolean resetPassword(Context context, String phone, String newPassword) {
        if (phone == null || newPassword == null || newPassword.trim().isEmpty()) return false;
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String hash = hashPassword(newPassword);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_PASS_HASH + phone.trim(), hash);
        editor.apply();
        return true;
    }

    public static void logout(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, false);
        editor.apply();
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.encodeToString(hash, Base64.NO_WRAP);
        } catch (NoSuchAlgorithmException e) {
            return password;
        }
    }
}
