package cn.com.timeriver.girls.main

import android.content.Context
import android.graphics.Color
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewStub
import android.widget.FrameLayout
import cn.com.timeriver.common.data.bean.Girl
import cn.com.timeriver.girls.R
import com.jude.easyrecyclerview.EasyRecyclerView
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter
import java.util.*

class GirlsView : FrameLayout, GirlsContract.View, RecyclerArrayAdapter.OnMoreListener, SwipeRefreshLayout.OnRefreshListener {

    private var active = false
    private var page = 1
    private val size = 20

    private var mNetworkErrorLayout: ViewStub
    private var mGirlsList: EasyRecyclerView
    private var networkErrorView: View? = null

    private var mData = ArrayList<Girl>()
    private val girlAdapter by lazy { GirlAdapter(context) }
    private lateinit var presenter: GirlsContract.Presenter

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        LayoutInflater.from(context).inflate(R.layout.view_girls_content, this, true)
        mNetworkErrorLayout = findViewById(R.id.network_error_layout)
        mGirlsList = findViewById(R.id.girls_recycler_view)
        mGirlsList.setLayoutManager(StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL))
        mGirlsList.setAdapterWithProgress(girlAdapter)
        mGirlsList.setRefreshingColor(Color.RED, Color.YELLOW, Color.GREEN)
        girlAdapter.setMore(R.layout.layout_load_more, this)
        girlAdapter.setNoMore(R.layout.layout_load_no_more)
        girlAdapter.setError(R.layout.layout_load_error)
        girlAdapter.setOnItemClickListener { position, holder ->
            //            val intent = Intent(, GirlActivity::class.java)
//            intent.putParcelableArrayListExtra(SyncStateContract.Constants.INTENT_GIRLS, mData)
//            intent.putExtra(SyncStateContract.Constants.INTENT_INDEX, position)
//            val options = ActivityOptionsCompat.makeScaleUpAnimation(holder.itemView, holder.itemView.width / 2, holder.itemView.height / 2, 0, 0)
//            Utils.getActivity(this@GirlsView).startActivity(intent, options.toBundle())
        }
        mGirlsList.setRefreshListener(this)

    }

    override fun setPresenter(presenter: GirlsContract.Presenter) {
        this.presenter = presenter
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        active = true
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        active = false
    }

    override fun isActive() = active

    override fun refresh(data: List<Girl>) {
        mData.clear()
        mData.addAll(data)
        girlAdapter.clear()
        girlAdapter.addAll(data)
    }

    override fun load(data: List<Girl>) {
        mData.addAll(data)
        girlAdapter.addAll(data)
    }

    override fun showError() {
        mGirlsList.showError()
        if (networkErrorView != null) {
            networkErrorView?.visibility = View.VISIBLE
            return
        }
        networkErrorView = mNetworkErrorLayout.inflate()
    }

    override fun showNormal() {
        if (networkErrorView != null) {
            networkErrorView?.visibility = View.GONE
        }
    }

    override fun onMoreShow() {
        if (mData.size % size == 0) {
            page++
            presenter.getGirls(size, page, false)
        }
    }

    override fun onMoreClick() {
    }

    override fun onRefresh() {
        presenter.getGirls(size, page, true)
        page = 1
    }

}