package com.example.whatsappclone;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class UserManager {

    protected static final String WHATSAPPUSER_ROW_NAME = "name";
    protected static final String WHATSAPPUSER_ROW_MOBILE = "mobile";
    protected static final String WHATSAPPUSER_ROW_STATUS = "status";
    protected static final String WHATSAPPUSER_ROW_PIC = "profilepic";

    private static UserManager userManager;

    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
        }
        return userManager;
    }

    private UserManager() {

    }

    private ParseObject currentUser;

    public ParseObject getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(ParseObject currentUser) {
        this.currentUser = currentUser;
    }

    public String getName() {
        if (currentUser == null) {
            return "";
        }
        return currentUser.getString(WHATSAPPUSER_ROW_NAME);
    }

    public String getStatus() {
        if (currentUser == null) {
            return "";
        }
        return currentUser.getString(WHATSAPPUSER_ROW_STATUS);
    }

    public int getMobileNumber() {
        if (currentUser == null) {
            return 0;
        }
        return currentUser.getInt(WHATSAPPUSER_ROW_MOBILE);
    }

    public ParseFile getProfilePic() {
        if (currentUser == null) {
            return null;
        }
        return currentUser.getParseFile(WHATSAPPUSER_ROW_PIC);
    }

    public void setName(String value, final ParseTaskCallBack taskCallBack) {
        if (currentUser == null) {
            return;
        }
        currentUser.put(WHATSAPPUSER_ROW_NAME, value);
        currentUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    if (taskCallBack != null) {
                        taskCallBack.onTaskDone();
                    }
                } else {
                    if (taskCallBack != null) {
                        taskCallBack.onTaskFailed(e);
                    }
                }
            }
        });
    }

    public void setStatus(String value, final ParseTaskCallBack taskCallBack) {
        if (currentUser == null) {
            return;
        }
        currentUser.put(WHATSAPPUSER_ROW_STATUS, value);
        currentUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    if (taskCallBack != null) {
                        taskCallBack.onTaskDone();
                    }
                } else {
                    if (taskCallBack != null) {
                        taskCallBack.onTaskFailed(e);
                    }
                }
            }
        });
    }

    public void setProfilePicture(ParseFile value, final ParseTaskCallBack taskCallBack) {
        if (currentUser == null) {
            return;
        }
        currentUser.put(WHATSAPPUSER_ROW_PIC, value);
        currentUser.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    if (taskCallBack != null) {
                        taskCallBack.onTaskDone();
                    }
                } else {
                    if (taskCallBack != null) {
                        taskCallBack.onTaskFailed(e);
                    }
                }
            }
        });
    }

    public interface ParseTaskCallBack {
        void onTaskDone();

        void onTaskFailed(Exception e);
    }
}