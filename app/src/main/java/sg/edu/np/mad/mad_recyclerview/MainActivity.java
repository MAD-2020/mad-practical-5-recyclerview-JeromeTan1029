package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sg.edu.np.mad.mad_recyclerview.ToDoList.ToDoListAdaptor;
import sg.edu.np.mad.mad_recyclerview.ToDoList.ToDoListItem;

public class MainActivity extends AppCompatActivity {

    private EditText addToDoItemText;
    private Button addToDoItemButton;

    private static final String TAG = "MainActivty";

    private ArrayList<ToDoListItem> toDoListItems = new ArrayList<ToDoListItem>();

    private List<String> toDoListTexts = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setTitle("To-Do List");

        addToDoItemText = findViewById(R.id.addToDoItemText);
        addToDoItemButton = findViewById(R.id.addToDoItemButton);

        toDoListTexts.add("Buy milk");
        toDoListTexts.add("Send postage");
        toDoListTexts.add("Buy Android development book");
        for (String txt : toDoListTexts){
            ToDoListItem item = new ToDoListItem(txt, false);
            toDoListItems.add(item);
        }

        final RecyclerView toDoListRecyclerView = findViewById(R.id.toDoListRecyclerView);
        final ToDoListAdaptor toDoListAdaptor = new ToDoListAdaptor(toDoListItems);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        toDoListRecyclerView.setLayoutManager(linearLayoutManager);
        toDoListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        toDoListRecyclerView.setAdapter(toDoListAdaptor);

        addToDoItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String txt = addToDoItemText.getEditableText().toString();
                if (!(txt.replace(" ", "").isEmpty())){
                    toDoListAdaptor.getData().add(new ToDoListItem(txt, false));
                    toDoListAdaptor.notifyItemInserted(toDoListAdaptor.getData().size() - 1);
                    toDoListAdaptor.notifyDataSetChanged();
                    addToDoItemText.setText("");
                    showNewEntry(toDoListRecyclerView, toDoListAdaptor.getData());
                    Log.v(TAG, "New Item Added");
                }
                else{
                    Toast.makeText(getApplicationContext(), "Invalid Item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *
     * @param rv RecyclerView for scrolling to
     * @param data ArrayList that was passed into RecyclerView
     */
    private void showNewEntry(RecyclerView rv, ArrayList data){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }
}
