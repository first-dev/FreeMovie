package com.hnd.freemovie.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class TabFragmentHelper {

    private FragmentManager fragmentManager;
    private int containerId;
    private FragmentTransaction fragmentTransaction;

    public TabFragmentHelper(int containerId, FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
    }

    public FragmentTransaction beginTransaction() {
        return fragmentManager.beginTransaction();
    }

    public void initializeFragment(Fragment fragment) {
        fragmentTransaction = beginTransaction();
        fragmentTransaction.add(containerId, fragment);
        fragmentTransaction.commit();
    }

    public void switchTo(Fragment fragment) {
        fragmentTransaction = beginTransaction();
        fragmentTransaction.hide(getVisibleFragment());
        if (fragment.isAdded()) {
            fragmentTransaction.show(fragment);
        } else {
            fragmentTransaction.add(containerId, fragment);
        }
        fragmentTransaction.commit();
    }

    public Fragment getVisibleFragment() {
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment != null && fragment.isVisible())
                return fragment;
        }
        return null;
    }

    public void addOnTop(Fragment fragment) {

    }
}
