package com.rishabh.expensetracker.service;

import com.rishabh.expensetracker.entity.Expense;
import com.rishabh.expensetracker.exception.ResourceNotFoundException;
import com.rishabh.expensetracker.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private UserService userService;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Override
    public Page<Expense> getAllExpenses(Pageable page) {
        return expenseRepository.findByUserId(userService.getLoggedInUser().getId(),page);
    }

    @Override
    public Expense getExpenseById(Long id) {
        Optional<Expense> exp = expenseRepository.findByUserIdAndId(userService.getLoggedInUser().getId() , id);
        if (exp.isPresent()){
            return exp.get();
        }
        throw new ResourceNotFoundException("Expense id not found");
    }

    @Override
    public void deleteExpenseById(Long id) {
        Optional<Expense> exp = expenseRepository.findByUserIdAndId(userService.getLoggedInUser().getId() ,id);
        if (exp.isPresent()){
            expenseRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("Expense is not found");
        }
    }

    @Override
    public Expense saveExpense(Expense e) {
        e.setUser(userService.getLoggedInUser());
        return expenseRepository.save(e);
    }

    @Override
    public Expense updateExpense(Long id, Expense e) {

        Expense ext = getExpenseById(id);
        if(ext==null){
            throw new RuntimeException("expense not found");
        }

        ext.setName(e.getName() !=null ? e.getName() : ext.getName());
        ext.setDesc(e.getDesc() !=null ? e.getDesc() : ext.getDesc());
        ext.setCategory(e.getName() !=null ? e.getCategory() : ext.getCategory());
        ext.setDate(e.getName() !=null ? e.getDate() : ext.getDate());
        ext.setAmount(e.getAmount() !=null ? e.getAmount() : ext.getAmount());
        expenseRepository.save(ext);

        return ext;
    }

    @Override
    public List<Expense> readByCategory(String category, Pageable page) {
       return expenseRepository.findByUserIdAndCategory(userService.getLoggedInUser().getId(),category,page).toList();

    }

    @Override
    public List<Expense> readByKeyword(String keyword, Pageable page) {
        return expenseRepository.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(),keyword,page).toList();
    }

    @Override
    public List<Expense> readByDate(Date start, Date end, Pageable page) {
        if (start == null) {
            start = new Date(0);
        }
        if (end == null) {
            end = new Date(System.currentTimeMillis());
        }

        return expenseRepository.findByUserIdAndDateBetween(userService.getLoggedInUser().getId(),start, end, page).toList();
    }
}
