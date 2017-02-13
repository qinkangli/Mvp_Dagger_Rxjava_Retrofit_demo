package com.dali.www.drrerxmvpdemo.ui.contract;

import com.dali.www.drrerxmvpdemo.base.BaseContract;
import com.dali.www.drrerxmvpdemo.bean.User;

import java.util.List;

/**
 * Created by qinkangli on 2017/2/9.
 */

public interface MainContract {

    interface View extends BaseContract.BaseView {
        void showHot(User user);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
        void getHot(String action,String doi);
    }
}
