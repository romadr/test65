package ru.test65.ui.workman.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.test65.R;
import ru.test65.data.bo.Workman;
import ru.test65.utils.CommonUtils;


public class WorkmanListAdapter extends RecyclerView.Adapter<WorkmanListAdapter.ViewHolder> {

    private final List<Workman> workmanList = new ArrayList<>();
    private Context context;
    private OnClickListener onClickListener;

    public WorkmanListAdapter(Context context, OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
        setHasStableIds(true);
    }

    public void setData(List<Workman> data) {
        workmanList.clear();
        workmanList.addAll(data);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.my_simple_list_item_2_with_icon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Workman workman = workmanList.get(position);

        if (workman == null) return;

        holder.itemView.setOnClickListener(view -> onClickListener.onClick(workman));

        holder.text1.setText(workman.getLName() + " " + workman.getFName());
        if (workman.getBirthday() != null) {
            int age = CommonUtils.calculateAge(workman.getBirthday());
            holder.text2.setText(age + " " + CommonUtils.getAgeSuffix(age));
        } else {
            holder.text2.setText("-");
        }
        if (workman.getAvatrUrl() != null && !workman.getAvatrUrl().isEmpty()) {
            Picasso.with(context).load(workman.getAvatrUrl()).placeholder(R.drawable.ic_person_black_48dp).into(holder.icon);
        } else {
            Picasso.with(context).load(R.drawable.ic_person_black_48dp).into(holder.icon);
        }
    }

    @Override
    public long getItemId(int i) {
        return workmanList.get(i).getId();
    }

    @Override
    public int getItemCount() {
        return workmanList.size();
    }

    public interface OnClickListener {
        void onClick(Workman workman);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.icon)
        ImageView icon;

        @BindView(R.id.text1)
        TextView text1;

        @BindView(R.id.text2)
        TextView text2;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }


}
