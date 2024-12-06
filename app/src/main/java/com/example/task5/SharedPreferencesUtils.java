package com.example.task5;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {
    private static String mFilePath = "mData";


    /***
     * 改变用户密码
     * @param context
     * @param username
     * @param userpassword 新密码
     */
    public static void ChangeUserInfo(Context context, String username, String userpassword) {
        SharedPreferences.Editor editor = context.getSharedPreferences(mFilePath, Context.MODE_PRIVATE).edit();
        editor.remove(username);
        editor.putString(username, userpassword);
        editor.apply();
    }


    /**
     * 用来保存用户注册的信息
     *
     * @param context      上下文
     * @param username     用户名
     * @param userpassword 密码
     * @return
     */

    public static void SaveUserInfo(Context context, String username, String userpassword) {
        SharedPreferences.Editor editor = context.getSharedPreferences(mFilePath, Context.MODE_PRIVATE).edit();
        editor.putString(username, userpassword);
        editor.apply();
    }


    /***
     * 读取信息
     * @param context
     * @param username 用户名
     * @return 密码
     */
    public static String getUserInfo(Context context, String username) {
        SharedPreferences spf = context.getSharedPreferences(mFilePath, Context.MODE_PRIVATE);
        return spf.getString(username, null);
    }

    /***
     * 检查用户是否存在
     * @param context
     * @param username 用户名
     * @return flag
     */
    public static boolean isExist(Context context, String username) {
        boolean flag = false;
        if (getUserInfo(context, username) != null) {
            flag = true;
        }
        return flag;
    }


}
