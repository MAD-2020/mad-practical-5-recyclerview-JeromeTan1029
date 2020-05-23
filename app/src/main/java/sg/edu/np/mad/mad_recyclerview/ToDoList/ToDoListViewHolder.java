package sg.edu.np.mad.mad_recyclerview.ToDoList;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import sg.edu.np.mad.mad_recyclerview.R;

public class ToDoListViewHolder extends RecyclerView.ViewHolder {
    TextView txt;
    CheckBox checkBox;

    public ToDoListViewHolder(View itemView){
        super(itemView);
        txt = itemView.findViewById(R.id.toDoListText);
        checkBox = itemView.findViewById(R.id.toDoListCheckbox);
    }
}
