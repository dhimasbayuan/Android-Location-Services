package com.ngi.jatenginyourhand.users;

public class NotifSubject {

    public String id;
    public String tujuanUser;
    public String userBerangkat;
    public String userKembali;
    public String notifUser;

    public NotifSubject(String id,String tujuanUser, String userBerangkat, String userKembali, String notifUser){
        this.id = id;
        this.tujuanUser = tujuanUser;
        this.userBerangkat = userBerangkat;
        this.userKembali = userKembali;
        this.notifUser = notifUser;
    }

}
