package com.yjbo.yjboandroidmodule.view.plug;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Created by lovexujh on 2017/7/25
 */
public class PluginManager {

    private static PluginManager ourInstance = new PluginManager();
    private Context context;

    private DexClassLoader pluginDexClassLoader,pluginDexClassLoaderSec;
    private Resources pluginResources,pluginResourcesSec;

    public PackageInfo getPluginPackageArchiveInfo(int i) {
        if (i == 1) {//第一个三方apk====即otherapk-debug.apk
            return pluginPackageArchiveInfo;
        }else if (i ==2){
            return pluginPackageArchiveInfoSec;
        }else {
            return  null;
        }
    }

    private PackageInfo pluginPackageArchiveInfo,pluginPackageArchiveInfoSec;

    public static PluginManager getInstance() {
        return ourInstance;
    }

    private PluginManager() {
    }

    public void setContext(Context context) {
        this.context = context.getApplicationContext();
    }

    public void loadApk(String dexPath,int i) {
        if (i == 1) {
            //(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent)
            pluginDexClassLoader = new DexClassLoader(dexPath, context.getDir("dex", Context.MODE_PRIVATE).getAbsolutePath(), null, context.getClassLoader());

            pluginPackageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(dexPath, PackageManager.GET_ACTIVITIES);

            //Resources(AssetManager assets, DisplayMetrics metrics, Configuration config) {
            AssetManager assets = null;
            try {
                assets = AssetManager.class.newInstance();
                Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
                addAssetPath.invoke(assets, dexPath);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            pluginResources = new Resources(assets, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        }else if (i ==2){
            //(String dexPath, String optimizedDirectory, String librarySearchPath, ClassLoader parent)
            pluginDexClassLoaderSec = new DexClassLoader(dexPath, context.getDir("dex", Context.MODE_PRIVATE).getAbsolutePath(), null, context.getClassLoader());

            pluginPackageArchiveInfoSec = context.getPackageManager().getPackageArchiveInfo(dexPath, PackageManager.GET_ACTIVITIES);

            //Resources(AssetManager assets, DisplayMetrics metrics, Configuration config) {
            AssetManager assets = null;
            try {
                assets = AssetManager.class.newInstance();
                Method addAssetPath = AssetManager.class.getMethod("addAssetPath", String.class);
                addAssetPath.invoke(assets, dexPath);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            pluginResourcesSec = new Resources(assets, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
        }
    }


    public DexClassLoader getPluginDexClassLoader(int i) {
        if (i == 1) {//第一个三方apk====即otherapk-debug.apk
            return pluginDexClassLoader;
        }else if (i ==2){
            return pluginDexClassLoaderSec;
        }else {
            return  null;
        }
    }

    public Resources getPluginResources(int i) {
        if (i == 1) {//第一个三方apk====即otherapk-debug.apk
            return pluginResources;
        }else if (i ==2){
            return pluginResourcesSec;
        }else {
            return  null;
        }
    }


}
