package sg.edu.np.mad.mad_recyclerview.ToDoList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import sg.edu.np.mad.mad_recyclerview.MainActivity;
import sg.edu.np.mad.mad_recyclerview.R;

public class ToDoListAdaptor extends RecyclerView.Adapter<ToDoListViewHolder> {

    private static final String TAG = "ToDoListAdaptor";

    private ArrayList<ToDoListItem> data;

    public ArrayList<ToDoListItem> getData() {
        return data;
    }



    public ToDoListAdaptor(ArrayList<ToDoListItem> input){
        data = input;
    }

    public ToDoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_list_viewholder, parent, false);
        return new ToDoListViewHolder(item);
    }

    public void onBindViewHolder(ToDoListViewHolder holder, final int position){
        ToDoListItem item = data.get(position);

        holder.txt.setText(item.getTxt());
        holder.checkBox.setChecked(item.isChecked());

        holder.txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder deleteBuilder = new AlertDialog.Builder(v.getContext());
                deleteBuilder.setTitle("Delete");
                deleteBuilder.setMessage(Html.fromHtml("<p style=\"text-align:center;\">Are you sure you want to delete <br/> <b>" + data.get(position).getTxt() + "</b>?</p>"));
                deleteBuilder.setCancelable(false);
                deleteBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notifyItemRemoved(position);
                        data.remove(position);
                        notifyItemRangeChanged(position, data.size());
                        Log.v(TAG, "Item Removed");
                    }
                });
                deleteBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(TAG, "User Cancelled Remove Item");
                    }
                });
                final AlertDialog deleteAlert = deleteBuilder.create();
                deleteAlert.show();
                Log.v(TAG, "Delete Confirmation Alert shown to User");
            }
        });
    }

    public int getItemCount(){
        return data.size();
    }
}
