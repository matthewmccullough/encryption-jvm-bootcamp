package com.mycompany.app;

import org.hibernate.Session;
import java.util.Date;
import java.util.Properties;

import com.mycompany.app.util.HibernateUtil;

public class EventManager {

    public static void main(String[] args) {
        EventManager mgr = new EventManager();

        if (args[0].equals("store")) {
			System.out.println("Creating and storing event...");
            mgr.createAndStoreEvent("My Event", new Date());
        } else {
			System.out.println("Hello World: "+System.getProperty("java.version","Unknown") );
		}

        HibernateUtil.getSessionFactory().close();
    }

    public void createAndStoreEvent(String title, Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);

        session.save(theEvent);

        session.getTransaction().commit();
    }

}