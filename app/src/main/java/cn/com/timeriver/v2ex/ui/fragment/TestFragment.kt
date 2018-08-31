package cn.com.timeriver.v2ex.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.com.timeriver.v2ex.R
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = "/test/fragment")
class TestFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context)
                .inflate(R.layout.fragment_test, container, false)
    }

}