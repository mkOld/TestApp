package com.excilys.testapp.android.persistence.dao.message.impl;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.excilys.testapp.android.model.message.Message;
import com.excilys.testapp.android.persistence.dao.message.MessageDao;
import com.excilys.testapp.android.persistence.dbhelper.MessageDbHelper;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by excilys on 13/06/14.
 */
@EBean(scope = EBean.Scope.Singleton)
public class MessageDaoImpl implements MessageDao {
    private final String TAG = MessageDaoImpl.class.getSimpleName();

    @RootContext
    Context context;

    @Override
    public void create(Message message) {
        Log.d(TAG, "Entering create in MessageDaoImpl");
        MessageDbHelper messageDbHelper = new MessageDbHelper(context);
        SQLiteDatabase db = messageDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MessageDbHelper.MESSAGE_ID, message.getId());
        values.put(MessageDbHelper.MESSAGE_CONTENT, message.getContent());
        values.put(MessageDbHelper.MESSAGE_SENDERID, message.getSenderId());
        try {
            db.insert(MessageDbHelper.MESSAGE_TABLE_NAME, null, values);
            db.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Leaving create in MessageDaoImpl");
    }

    @Override
    public List<Message> retrieve() {
        Log.d(TAG, "Entering retrieve in MessageDaoImpl");
        List<Message> messages = new ArrayList<>();
        MessageDbHelper messageDbHelper = new MessageDbHelper(context);
        SQLiteDatabase db = messageDbHelper.getReadableDatabase();
        String retrieve = "SELECT * FROM message;";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(retrieve, null);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        while (cursor.moveToNext()) {
            messages.add(Message.builder().id(cursor.getString(0))
                    .content(cursor.getString(1))
                    .senderId(cursor.getString(2))
                    .build());
        }
        if (cursor != null) {
            cursor.close();
        }
        try {
        db.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Leaving retrieve in MessageDaoImpl");
        return messages;
    }

    @Override
    public void update(Message message) {
        Log.d(TAG, "Entering update in MessageDaoImpl");
        MessageDbHelper messageDbHelper = new MessageDbHelper(context);
        SQLiteDatabase db = messageDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MessageDbHelper.MESSAGE_ID, message.getId());
        values.put(MessageDbHelper.MESSAGE_CONTENT, message.getContent());
        values.put(MessageDbHelper.MESSAGE_SENDERID, message.getSenderId());
        try {
            db.update(MessageDbHelper.MESSAGE_TABLE_NAME, values, MessageDbHelper.MESSAGE_ID + " = ?", new String[]{message.getId().toString()});
            db.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "Leaving update in MessageDaoImpl");
    }

    @Override
    public void delete(String id) {
        Log.d(TAG, "Entering delete in MessageDaoImpl");
        MessageDbHelper messageDbHelper = new MessageDbHelper(context);
        SQLiteDatabase db = messageDbHelper.getWritableDatabase();
        try {
            db.delete(MessageDbHelper.MESSAGE_TABLE_NAME, MessageDbHelper.MESSAGE_ID + " = ?", new String[]{id.toString()});
            db.close();
        } catch (NullPointerException e) {
        e.printStackTrace();
        }
        Log.d(TAG, "Leaving delete in MessageDaoImpl");
    }
}
