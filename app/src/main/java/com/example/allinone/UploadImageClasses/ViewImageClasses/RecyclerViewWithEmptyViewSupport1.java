package com.example.allinone.UploadImageClasses.ViewImageClasses;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewWithEmptyViewSupport1 extends RecyclerView {
    private View emptyView;

    public RecyclerViewWithEmptyViewSupport1(Context context) {
        super(context);
    }

    public RecyclerViewWithEmptyViewSupport1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewWithEmptyViewSupport1(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    void checkIfEmpty(){
        RecyclerView.Adapter<?> adapter = getAdapter();
        if (adapter != null && emptyView != null){
            if (adapter.getItemCount() == 0){
                this.setVisibility(GONE);
                emptyView.setVisibility(VISIBLE);
            } else {
                this.setVisibility(VISIBLE);
                emptyView.setVisibility(GONE);
            }
        }
    }
    public void setEmptyView(View view){
        emptyView = view;
    }

    @Override
    public void setAdapter(RecyclerView.Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter != null)
            adapter.registerAdapterDataObserver(observer);
        checkIfEmpty();
    }

    AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            checkIfEmpty();
        }
        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            checkIfEmpty();
        }
        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            checkIfEmpty();
        }
        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            checkIfEmpty();
        }
        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            checkIfEmpty();
        }
    };

}
