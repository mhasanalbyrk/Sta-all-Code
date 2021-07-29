package day4.Exercise;

import java.util.ArrayList;
import java.util.List;

public class PostOffice {
    private List<MailReceiver> subs ;

    public PostOffice(){
        this.subs = new ArrayList<MailReceiver>();
    }

    public List<MailReceiver> getSubs() {
        return subs;
    }
    public void addListener(MailReceiver p){
        subs.add(p);
    }

    public void mailArrived(Mail m){
        for (MailReceiver p: subs) {
            p.onNewMailArrived(m);
        }
    }
    public void removeListener(MailReceiver p){
        subs.remove(p);
    }

}
