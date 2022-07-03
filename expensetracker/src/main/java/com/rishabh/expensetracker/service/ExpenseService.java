package com.rishabh.expensetracker.service;


import com.rishabh.expensetracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface ExpenseService {

    Page<Expense> getAllExpenses(Pageable page);
    Expense getExpenseById(Long id);

    void deleteExpenseById(Long id);

    Expense saveExpense(Expense e);

    Expense updateExpense(Long id,Expense e);

    List<Expense>  readByCategory(String category,Pageable page);

    List<Expense> readByKeyword(String keyword,Pageable page);

    List<Expense> readByDate(Date start,Date end,Pageable page);
}
