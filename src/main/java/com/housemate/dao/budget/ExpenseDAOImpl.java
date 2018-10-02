package com.housemate.dao.budget;

import com.housemate.models.Expense;
import com.housemate.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.DateFormat;
import java.util.Date;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

@Repository
public class ExpenseDAOImpl implements ExpenseDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Expense> getExpense(User user, String month, int year) throws ParseException {
        Session session = sessionFactory.getCurrentSession();

        Date date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(month);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        String select = "FROM Expense E WHERE MONTH(E.createdOn) = :month AND YEAR(E.createdOn) = :year AND E.user.id = :userId";
        Query query = session.createQuery(select, Expense.class);

        query.setParameter("month", cal.get(Calendar.MONTH) + 1);
        query.setParameter("year", year);
        query.setParameter("userId", user.getId());
        List<Expense> monthBudget = query.list();

        return monthBudget;
    }

    @Override
    public List<Expense> getExpense(User user) {
        Session session = sessionFactory.getCurrentSession();
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        String select = "FROM Expense E WHERE MONTH(E.createdOn) = :month AND YEAR(E.createdOn) = :year AND E.user.id = :userId";
        Query query = session.createQuery(select, Expense.class);

        query.setParameter("month", currentMonth);
        query.setParameter("year", currentYear);
        query.setParameter("userId", user.getId());

        List<Expense> thisMonthsBudget = query.list();

        return thisMonthsBudget;
    }

    @Override
    public void newExpense(User user, BigDecimal amount, String name, Date date) {
        Session session = sessionFactory.getCurrentSession();

        Expense newExpense = new Expense(user, amount, name, date);

        try{
            session.save(newExpense);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void newExpense(User user, BigDecimal amount, String name) {
        Session session = sessionFactory.getCurrentSession();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        Expense newExpense = new Expense(user, amount, name, date);

        try{
            session.save(newExpense);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
