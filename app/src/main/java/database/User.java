package database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "User_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    public  int user_id;

    @ColumnInfo(name = "name")
    public  String user_name;

    @ColumnInfo(name = "age")
    public  int age;

    @ColumnInfo(name = "activity")
    public boolean isActive;

    @ColumnInfo(name = "selection")
    public boolean isSelected;

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                ", isSelected=" + isSelected +
                '}';
    }
}
