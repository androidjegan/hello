package vijaytally.vijay.com.todo;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesListAdapter extends RecyclerView.Adapter<NotesListAdapter.ViewHolder> {

    ArrayList<Noteslist> noteslist;
    Context context;

    public NotesListAdapter(Todo todo, ArrayList<Noteslist> noteslists) {
        this.noteslist=noteslists;
        this.context=todo;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemLayoutView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notes_list, null);
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int i) {
        viewHolder.notes_TextView.setText(noteslist.get(i).getName());
        viewHolder.edit_TextView.setText("edit");
        viewHolder.delete_TextView.setText("delete");

        viewHolder.delete_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (viewHolder.checkBox.isChecked()){
                    Log.e("in","sa"+isChecked);
                    viewHolder.notes_TextView.setPaintFlags(viewHolder.notes_TextView.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
                }else {
                    Log.e("in","sas"+isChecked);
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return noteslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView notes_TextView,edit_TextView,delete_TextView;
        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = (CheckBox)itemView.findViewById(R.id.Select_CheckBox);
            notes_TextView = (TextView)itemView.findViewById(R.id.notes_TextView);
            edit_TextView = (TextView)itemView.findViewById(R.id.edit_TextView);
            delete_TextView = (TextView)itemView.findViewById(R.id.delete_TextView);

        }
    }
}
