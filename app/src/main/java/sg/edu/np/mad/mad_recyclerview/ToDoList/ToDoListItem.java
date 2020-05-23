package sg.edu.np.mad.mad_recyclerview.ToDoList;

public class ToDoListItem {

    private String txt;
    private boolean checked;

    public ToDoListItem(String txt, boolean checked){
        this.txt = txt;
        this.checked = checked;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
