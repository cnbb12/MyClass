package com.example.a95797.myclass.utils;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a95797.myclass.R;

public class LoadMoreRecyclerView
        extends RecyclerView
{
    public static final int TYPE_FOOTER = 2;
    public static final int TYPE_HEADER = 1;
    public static final int TYPE_LIST = 3;
    public static final int TYPE_NORMAL = 0;
    public static final int TYPE_STAGGER = 4;
    private AutoLoadAdapter mAutoLoadAdapter;
    private boolean mIsFooterEnable = false;
    private boolean mIsLoadingMore;
    private LoadMoreListener mListener;
    private int mLoadMorePosition;

    public LoadMoreRecyclerView(Context paramContext)
    {
        super(paramContext);
        init();
    }

    public LoadMoreRecyclerView(Context paramContext, AttributeSet paramAttributeSet)
    {
        super(paramContext, paramAttributeSet);
        init();
    }

    public LoadMoreRecyclerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
    {
        super(paramContext, paramAttributeSet, paramInt);
        init();
    }

    private int getFirstVisiblePosition()
    {
        if ((getLayoutManager() instanceof LinearLayoutManager)) {
            return ((LinearLayoutManager)getLayoutManager()).findFirstVisibleItemPosition();
        }
        if ((getLayoutManager() instanceof GridLayoutManager)) {
            return ((GridLayoutManager)getLayoutManager()).findFirstVisibleItemPosition();
        }
        if ((getLayoutManager() instanceof StaggeredGridLayoutManager))
        {
            StaggeredGridLayoutManager localStaggeredGridLayoutManager = (StaggeredGridLayoutManager)getLayoutManager();
            return getMinPositions(localStaggeredGridLayoutManager.findFirstVisibleItemPositions(new int[localStaggeredGridLayoutManager.getSpanCount()]));
        }
        return 0;
    }

    private int getLastVisiblePosition()
    {
        if ((getLayoutManager() instanceof LinearLayoutManager)) {
            return ((LinearLayoutManager)getLayoutManager()).findLastVisibleItemPosition();
        }
        if ((getLayoutManager() instanceof GridLayoutManager)) {
            return ((GridLayoutManager)getLayoutManager()).findLastVisibleItemPosition();
        }
        if ((getLayoutManager() instanceof StaggeredGridLayoutManager))
        {
            StaggeredGridLayoutManager localStaggeredGridLayoutManager = (StaggeredGridLayoutManager)getLayoutManager();
            return getMaxPosition(localStaggeredGridLayoutManager.findLastVisibleItemPositions(new int[localStaggeredGridLayoutManager.getSpanCount()]));
        }
        return getLayoutManager().getItemCount() - 1;
    }

    private int getMaxPosition(int[] paramArrayOfInt)
    {
        int k = paramArrayOfInt.length;
        int j = Integer.MIN_VALUE;
        int i = 0;
        while (i < k)
        {
            j = Math.max(j, paramArrayOfInt[i]);
            i += 1;
        }
        return j;
    }

    private int getMinPositions(int[] paramArrayOfInt)
    {
        int k = paramArrayOfInt.length;
        int j = Integer.MAX_VALUE;
        int i = 0;
        while (i < k)
        {
            j = Math.min(j, paramArrayOfInt[i]);
            i += 1;
        }
        return j;
    }

    private void init()
    {
        super.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            public void onScrollStateChanged(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt)
            {
                super.onScrollStateChanged(paramAnonymousRecyclerView, paramAnonymousInt);
            }

            public void onScrolled(RecyclerView paramAnonymousRecyclerView, int paramAnonymousInt1, int paramAnonymousInt2)
            {
                super.onScrolled(paramAnonymousRecyclerView, paramAnonymousInt1, paramAnonymousInt2);
                if ((LoadMoreRecyclerView.this.mListener != null) && (LoadMoreRecyclerView.this.mIsFooterEnable) && (!LoadMoreRecyclerView.this.mIsLoadingMore) && (paramAnonymousInt2 > 0))
                {
                    paramAnonymousInt1 = LoadMoreRecyclerView.this.getLastVisiblePosition();
                    if (paramAnonymousInt1 + 1 == LoadMoreRecyclerView.this.mAutoLoadAdapter.getItemCount())
                    {
                        LoadMoreRecyclerView.this.setLoadingMore(true);
                       //TODO 滑动下拉

                        LoadMoreRecyclerView.this.mLoadMorePosition=paramAnonymousInt1;
                        //LoadMoreRecyclerView.access$502(LoadMoreRecyclerView.this, paramAnonymousInt1);
                        LoadMoreRecyclerView.this.mListener.onLoadMore();
                    }
                }
            }
        });
    }

    public void addHeaderView(int paramInt)
    {
        this.mAutoLoadAdapter.addHeaderView(paramInt);
    }

    public void notifyMoreFinish(boolean paramBoolean)
    {
        setAutoLoadMoreEnable(paramBoolean);
        getAdapter().notifyItemRemoved(this.mLoadMorePosition);
        this.mIsLoadingMore = false;
    }

    public void setAdapter(RecyclerView.Adapter paramAdapter)
    {
        if (paramAdapter != null) {
            this.mAutoLoadAdapter = new AutoLoadAdapter(paramAdapter);
        }
        super.swapAdapter(this.mAutoLoadAdapter, true);
    }

    public void setAutoLoadMoreEnable(boolean paramBoolean)
    {
        this.mIsFooterEnable = paramBoolean;
    }

    public void setHeaderEnable(boolean paramBoolean)
    {
        this.mAutoLoadAdapter.setHeaderEnable(paramBoolean);
    }

    public void setLoadMoreListener(LoadMoreListener paramLoadMoreListener)
    {
        this.mListener = paramLoadMoreListener;
    }

    public void setLoadingMore(boolean paramBoolean)
    {
        this.mIsLoadingMore = paramBoolean;
    }

    public void switchLayoutManager(RecyclerView.LayoutManager paramLayoutManager)
    {
        int i = getFirstVisiblePosition();
        setLayoutManager(paramLayoutManager);
        getLayoutManager().scrollToPosition(i);
    }

    public class AutoLoadAdapter
            extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    {
        private int mHeaderResId;
        private RecyclerView.Adapter mInternalAdapter;
        private boolean mIsHeaderEnable;

        public AutoLoadAdapter(RecyclerView.Adapter paramAdapter)
        {
            this.mInternalAdapter = paramAdapter;
            this.mIsHeaderEnable = false;
        }

        public void addHeaderView(int paramInt)
        {
            this.mHeaderResId = paramInt;
        }

        public int getItemCount()
        {
            int j = this.mInternalAdapter.getItemCount();
            int i = j;
            if (LoadMoreRecyclerView.this.mIsFooterEnable) {
                i = j + 1;
            }
            j = i;
            if (this.mIsHeaderEnable) {
                j = i + 1;
            }
            return j;
        }

        public int getItemViewType(int paramInt)
        {
            int i = getItemCount();
            if ((paramInt == 0) && (this.mIsHeaderEnable) && (this.mHeaderResId > 0)) {
                return 1;
            }
            if ((i - 1 == paramInt) && (LoadMoreRecyclerView.this.mIsFooterEnable)) {
                return 2;
            }
            if ((LoadMoreRecyclerView.this.getLayoutManager() instanceof LinearLayoutManager)) {
                return 3;
            }
            if ((LoadMoreRecyclerView.this.getLayoutManager() instanceof StaggeredGridLayoutManager)) {
                return 4;
            }
            return 0;
        }

        public void onBindViewHolder(RecyclerView.ViewHolder paramViewHolder, int paramInt)
        {
            int i = getItemViewType(paramInt);
            if ((i != 2) && (i != 1)) {
                this.mInternalAdapter.onBindViewHolder(paramViewHolder, paramInt);
            }
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup paramViewGroup, int paramInt)
        {
            if (paramInt == 1) {
                return new HeaderViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(this.mHeaderResId, paramViewGroup, false));
            }
            if (paramInt == 2) {
                return new FooterViewHolder(LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.loading, paramViewGroup, false));
            }
            return this.mInternalAdapter.onCreateViewHolder(paramViewGroup, paramInt);
        }

        public void setHeaderEnable(boolean paramBoolean)
        {
            this.mIsHeaderEnable = paramBoolean;
        }

        public class FooterViewHolder
                extends RecyclerView.ViewHolder
        {
            public FooterViewHolder(View itemView) {
                super(itemView);
            }
        }

        public class HeaderViewHolder
                extends RecyclerView.ViewHolder
        {
            public HeaderViewHolder(View itemView) {
                super(itemView);
            }
        }
    }

    public static abstract interface LoadMoreListener
    {
        public abstract void onLoadMore();
    }
}
