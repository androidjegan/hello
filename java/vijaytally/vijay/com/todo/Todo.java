package vijaytally.vijay.com.todo;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class Todo extends Activity {

    EditText add_note_EditText;
    TextView add_TextView;
    RecyclerView notes_List_RecyclerView;
    NotesListAdapter notesListAdapter;
    SQLiteDatabase db;
    ArrayList<Noteslist> noteslists=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        db = openOrCreateDatabase("note.db",MODE_APPEND,null);
        db.execSQL("Create table if not exists notes(id integer primary key autoincrement,note VARCHAR);");

        add_note_EditText = (EditText)findViewById(R.id.add_note_EditText);
        add_TextView = (TextView)findViewById(R.id.add_TextView);
        notes_List_RecyclerView = (RecyclerView)findViewById(R.id.notes_List_RecyclerView);

        noteslists.clear();
        Cursor c = db.rawQuery("select note from notes",null);
        if (c.moveToFirst()) {
            do {
                Noteslist notelist = new Noteslist();
                notelist.setName(c.getString(c.getColumnIndex("note")));
                noteslists.add(notelist);
            } while (c.moveToNext());
        }

        notesListAdapter = new NotesListAdapter(this,noteslists);
        notes_List_RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        notes_List_RecyclerView.setAdapter(notesListAdapter);
        notes_List_RecyclerView.setHasFixedSize(true);

        notesListAdapter.notifyDataSetChanged();
        add_TextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (add_note_EditText.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"empty",Toast.LENGTH_LONG).show();
                }else {
                    db.execSQL("insert into notes (note) values('"+add_note_EditText.getText().toString()+"');");
                    Toast.makeText(getApplicationContext(),"insert",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
