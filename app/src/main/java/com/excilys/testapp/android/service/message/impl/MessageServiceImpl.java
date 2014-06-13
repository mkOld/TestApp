package com.excilys.testapp.android.service.message.impl;

import android.util.Log;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import java.util.List;
import com.excilys.testapp.android.service.utils.Utils;
import com.excilys.testapp.android.model.message.Message;
import com.excilys.testapp.android.persistence.dao.message.MessageDao;
import com.excilys.testapp.android.persistence.dao.message.impl.MessageDaoImpl;
import com.excilys.testapp.android.service.message.MessageService;

/**
 * Created by excilys on 13/06/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class MessageServiceImpl implements MessageService {
    private final String TAG = MessageServiceImpl.class.getSimpleName();

    @Bean(MessageDaoImpl.class)
    MessageDao messageDao;

    @Override
    public void create(Message message) {
        Log.d(TAG, "Entering create in MessageServiceImpl");

        if (message.getId() == null) {
            message.setId(Utils.doUUID());
        }

        messageDao.create(message);

        Log.d(TAG, "Leaving update in MessageServiceImpl");
    }

    @Override
    public List<Message> retrieve() {
        Log.d(TAG, "Entering create in MessageServiceImpl");
        List<Message> messages = messageDao.retrieve();

        Log.d(TAG, "Leaving update in MessageServiceImpl");
        return messages;
    }

    @Override
    public void update(Message message) {
        Log.d(TAG, "Entering create in MessageServiceImpl");
        messageDao.update(message);
        Log.d(TAG, "Leaving update in MessageServiceImpl");
    }

    @Override
    public void delete(String id) {
        Log.d(TAG, "Entering create in MessageServiceImpl");
        messageDao.delete(id);
        Log.d(TAG, "Leaving update in MessageServiceImpl");
    }
}

