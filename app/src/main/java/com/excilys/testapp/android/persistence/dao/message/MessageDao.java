package com.excilys.testapp.android.persistence.dao.message;


import com.excilys.testapp.android.model.message.Message;

import java.util.List;

/**
 * Created by excilys on 13/06/14.
 */
public interface MessageDao {

    public void create(Message message);

    public List<Message> retrieve();

    public void update(Message message);

    public void delete(String id);
}

