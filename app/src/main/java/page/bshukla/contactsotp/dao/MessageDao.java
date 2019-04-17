package page.bshukla.contactsotp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import page.bshukla.contactsotp.model.Message;

import java.util.List;

@Dao
public interface MessageDao {

    @Insert
    void insertMessage(Message message);

    @Query("SELECT * FROM Message ORDER BY id")
    LiveData<List<Message>> getAllMessages();

    @Query("SELECT * FROM Message WHERE id =:messageId")
    LiveData<Message> getMessageById(int messageId);

    @Update
    void updateMessage(Message message);

    @Delete
    void deleteMessage(Message message);
}
