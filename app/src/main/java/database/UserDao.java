package database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM User_table")
    List<User> getAllUsers();

//    @Query("SELECT * FROM User_table WHERE selection = 'true'")
//    List<User> getSelectedUsers();

    @Insert
    void insertUser(User user);

    @Query("DELETE FROM User_table where selection =:selection")
    void deleteSelectedUsers(boolean selection);

    @Query("UPDATE User_table SET selection = :isChecked WHERE user_id = :id")
    void updateSelectedUser(boolean isChecked, int id);

    @Query("SELECT * FROM User_table WHERE selection = :selection")
    List<User> showSelectedData(boolean selection);

    @Delete
    void delete(User user);

}
