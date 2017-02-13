package com.dali.www.drrerxmvpdemo.component;

import com.dali.www.drrerxmvpdemo.ui.MainActivity;

import dagger.Component;

/**
 * Created by qinkangli on 2017/2/9.
 */
@Component(dependencies = AppComponent.class)
public interface MainComponent {

    MainActivity inject(MainActivity mainActivity);

}
