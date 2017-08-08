package xyz.ankit.library;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * This is sort of wrapper that give flexibility to not define your own implementation of RecyclerView.Adapter.
 * it requires data binding to be enabled.
 * Created by ankit on 19-07-2017.
 */

public class RecyclerDataBindingAdapter<T> extends RecyclerView.Adapter<RecyclerDataBindingAdapter.GenericViewHolder> {
    private List<T> list;
    private int layout;
    private OnClickListener<T> clickListener;
    private int variableId;

    public interface OnClickListener<T> {
        void onItemClick(int pos, T t);
    }

    public RecyclerDataBindingAdapter(int layout, int variableId) {
        this.layout = layout;
        this.variableId = variableId;
    }

    public RecyclerDataBindingAdapter(int layout, int variableId, OnClickListener<T> clickListener) {
        this.layout = layout;
        this.clickListener = clickListener;
        this.variableId = variableId;
    }

    public RecyclerDataBindingAdapter(int layout, int variableId, OnClickListener<T> clickListener, List<T> list) {
        this.list = list;
        this.layout = layout;
        this.clickListener = clickListener;
        this.variableId = variableId;
    }

    /**
     * Returns underlying List
     * */
    public List<T> getList() {
        return list;
    }

    /**
     * replaces the underlying list
     * @param list list to replace
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public RecyclerDataBindingAdapter.GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new RecyclerDataBindingAdapter.GenericViewHolder(layoutInflater.inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerDataBindingAdapter.GenericViewHolder holder, int position) {
        holder.viewDataBinding.setVariable(variableId, list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * View holder and click listener
     */
    class GenericViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding viewDataBinding;

        GenericViewHolder(View itemView) {
            super(itemView);
            viewDataBinding = DataBindingUtil.bind(itemView);
            if (clickListener != null) {
                viewDataBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        GenericViewHolder.this.onClick(v);
                    }
                });
            }
        }

        void onClick(View c) {
            if (clickListener != null) {
                clickListener.onItemClick(getAdapterPosition(), list.get(getAdapterPosition()));
            }
        }
    }
}
