package ru.test65.ui.specialty;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.test65.R;
import ru.test65.data.bo.Specialty;


public class SpecialtyListAdapter extends RecyclerView.Adapter<SpecialtyListAdapter.ViewHolder> {

    private final List<Specialty> specialtyList = new ArrayList<>();
    private Context context;
    private OnClickListener onClickListener;

    public SpecialtyListAdapter(Context context, OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
        setHasStableIds(true);
    }

    public void setData(List<Specialty> data) {
        specialtyList.clear();
        specialtyList.addAll(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.my_simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Specialty specialty = specialtyList.get(position);
        if (specialty == null) return;

        holder.itemView.setOnClickListener(view -> onClickListener.onClick(specialty));
        holder.text1.setText(specialty.getName());
    }

    @Override
    public long getItemId(int i) {
        return specialtyList.get(i).getSpecialtyId();
    }

    @Override
    public int getItemCount() {
        return specialtyList.size();
    }

    public interface OnClickListener {
        void onClick(Specialty specialty);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text1)
        TextView text1;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }


}
